import React from 'react'
import { Link } from 'react-router-dom'

const Note = ({ note }) => {
  return (
    <Link to={`/notes/${note.id}`} className="contact__item">
            <div className="contact__header">
                <div className="contact__image">
                    <img src={note.photoUrl} alt={note.name}  />
                </div>
                <div className="contact__details">
                    <p className="note_name">{note.name.substring(0, 15)} </p>
                    <p className="note_title">{note.title}</p>
                </div>
            </div>
            <div className="contact__body">
                <p><i className="bi bi-envelope"></i> {note.email.substring(0, 20)} </p>
                <p><i className="bi bi-geo"></i> {note.address}</p>
                <p><i className="bi bi-telephone"></i> {note.phone}</p>
                <p>{note.status === 'Active' ? <i className='bi bi-check-circle'></i> : 
                    <i className='bi bi-x-circle'></i>} {note.status}</p>
            </div>
        </Link>
  )
}

export default Note