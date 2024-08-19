import React from 'react';
import Note from "./Note";

const NoteList = ({ noteData, currentNotePage, getAllNotes }) => {
    return (
        <main className='main'>
            {noteData?.content?.length === 0 && <div>No Notes. Please add a new note</div>}
            <ul className='note__list'>
                {noteData?.content?.length > 0 && noteData.content.map(note => <Note note={note} key={note.id} />)}
            </ul>
            {
            noteData?.content?.length > 0 && noteData?.totalPages > 1 &&
                <div className='pagination'>
                    <a onClick={() => getAllNotes(currentNotePage - 1)} className={currentNotePage === 0 ? 'disabled' : ''} aria-label="Previous page">
                        &laquo;
                    </a>
                    {noteData && [...Array(noteData.totalPages).keys()].map((page) =>
                        <a onClick={() => getAllNotes(page)} className={currentNotePage === page ? 'active' : ''} key={page} aria-label={`Page ${page + 1}`}>
                            {page + 1}
                        </a>
                    )}
                    <a onClick={() => getAllNotes(currentNotePage + 1)} className={noteData.totalPages === currentNotePage + 1 ? 'disabled' : ''} aria-label="Next page">
                        &raquo;
                    </a>
                </div>
            }
        </main>
    );
};

export default NoteList;
