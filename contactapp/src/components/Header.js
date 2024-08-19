import React from 'react';

const Header = ({ toggleContactModal, toggleNoteModal, nbOfContacts, nbOfNotes }) => {
  return (
    <header className='header'>
        <div className='container'>
            <div className='header__section'>
                <h3>Contact List ({nbOfContacts})</h3>
                <button 
                    onClick={() => toggleContactModal(true)} 
                    className='btn' 
                    aria-label="Add new contact"
                >
                    <i className='bi bi-plus-square'></i> Add New Contact
                </button>
            </div>

            <div className='header__section'>
                <h3>Notes List ({nbOfNotes})</h3>
                <button 
                    onClick={() => toggleNoteModal(true)} 
                    className='btn' 
                    aria-label="Add new note"
                >
                    <i className='bi bi-plus-square'></i> Add New Note
                </button>
            </div>
        </div>
    </header>
  )
}

export default Header;
