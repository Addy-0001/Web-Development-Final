import React from 'react'

const Header = ({ toggleContactModal, toggleNoteModal, nbOfContacts, nbOfNotes }) => {
  return (
    <header className='header'>
        <div className='container'>
            <h3>Contact List ({nbOfContacts})</h3>
            <button onClick={() => toggleContactModal(true)} className='btn'>
                <i className='bi bi-plus-square'></i> Add New Contact
            </button>

            <h3>Notes List ({nbOfNotes})</h3>
            <button onClick={() => toggleNoteModal(true)} className='btn'>
                <i className='bi bi-plus-square'></i> Add New Note
            </button>
        </div>
    </header>
  )
}

export default Header