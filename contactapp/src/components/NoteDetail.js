import React, { useState, useEffect, useRef } from 'react';
import { Link, useParams } from 'react-router-dom';
import { getNote } from '../api/NoteService';
import { toastError, toastSuccess } from '../api/ToastService';

const NoteDetail = ({ updateNote, updateNoteImage }) => {
    const inputRef = useRef();
    const [note, setNote] = useState({
        id: '',
        title: '',
        content: '',
        status: '',
        photoUrl: ''
    });
    const { id } = useParams();
    const fetchNote = async (id) => {
        try {
            const { data } = await getNote(id);
            setNote(data);
        } catch (error) {
            toastError(error.message);
        }
    };
    const selectImage = () => {
        inputRef.current.click();
    };
    const updatePhoto = async (file) => {
        try {
            const formData = new FormData();
            formData.append('file', file, file.name);
            formData.append('id', id);
            await updateNoteImage(formData);
            setNote((prev) => ({ ...prev, photoUrl: `${prev.photoUrl}?updated_at=${new Date().getTime()}` }));
            toastSuccess('Photo updated');
        } catch (error) {
            toastError(error.message);
        }
    };
    const onChange = (event) => {
        setNote({ ...note, [event.target.name]: event.target.value });
    };
    const onUpdateNote = async (event) => {
        event.preventDefault();
        await updateNote(note);
        fetchNote(id);
        toastSuccess('Note Updated');
    };
    useEffect(() => {
        fetchNote(id);
    }, [id]);
    return (
        <>
            <Link to='/notes' className='link'><i className='bi bi-arrow-left'></i> Back to list</Link>
            <div className='note__profile'>
                <div className='note__profile-details'>
                    <img src={note.photoUrl} alt={`Note titled ${note.title}`} />
                    <div className='note__metadata'>
                        <p className='note__name'>{note.title}</p>
                        <p className='note__muted'>JPG, GIF, or PNG. Max size of 10MB</p>
                        <button onClick={selectImage} className='btn'><i className='bi bi-cloud-upload'></i> Change Photo</button>
                    </div>
                </div>
                <div className='note__settings'>
                    <form onSubmit={onUpdateNote} className="form">
                        <div className="note-details">
                            <input type="hidden" defaultValue={note.id} name="id" required />
                            <div className="input-box">
                                <span className="details">Title</span>
                                <input type="text" value={note.title} onChange={onChange} name="title" required />
                            </div>
                            <div className="input-box">
                                <span className="details">Content</span>
                                <textarea value={note.content} onChange={onChange} name="content" required />
                            </div>
                            <div className="input-box">
                                <span className="details">Status</span>
                                <input type="text" value={note.status} onChange={onChange} name="status" required />
                            </div>
                        </div>
                        <div className="form_footer">
                            <button type="submit" className="btn">Save</button>
                        </div>
                    </form>
                </div>
            </div>

            <form style={{ display: 'none' }}>
                <input type='file' ref={inputRef} onChange={(event) => updatePhoto(event.target.files[0])} name='file' accept='image/*' />
            </form>
        </>
    );
};

export default NoteDetail;
