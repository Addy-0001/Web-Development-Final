import React from 'react';
import Note from "./Note"

const NoteList = ({ data, currentPage, getAllNotes }) => {
    return (
        <main className='main'>
            {data?.content?.length === 0 && <div>No Notes. Please add a new note</div>}

            <ul className='note__list'>
                {data?.content?.length > 0 && data.content.map(note => <Note note={note} key={note.id} />)}
            </ul>

            {data?.content?.length > 0 && data?.totalPages > 1 &&
            <div className='pagination'>
                <a onClick={() => getAllNotes(currentPage - 1)} className={0 === currentPage ? 'disabled' : ''}>&laquo;</a>

                { data && [...Array(data.totalPages).keys()].map((page, index) => 
                    <a onClick={() => getAllNotes(page)} className={currentPage === page ? 'active' : ''} key={page}>{page + 1}</a>)}


                <a onClick={() => getAllNotes(currentPage + 1)} className={data.totalPages === currentPage + 1 ? 'disabled' : ''}>&raquo;</a>
            </div>            
            }

        </main>
    )
}

export default NoteList