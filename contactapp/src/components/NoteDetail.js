import React, { useState, useEffect, useRef } from 'react';
import { Link, useParams } from 'react-router-dom';
import { getNote } from '../api/NoteService';
import { toastError, toastSuccess } from '../api/ToastService';

const NoteDetail = ({ updateNote, updateImage }) => {
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
            console.log(data);
            //toastSuccess('Note retrieved');
        } catch (error) {
            console.log(error);
            toastError(error.message);
        }
    };

    const selectImage = () => {
        inputRef.current.click();
    };

    const udpatePhoto = async (file) => {
        try {
            const formData = new FormData();
            formData.append('file', file, file.name);
            formData.append('id', id);
            await updateImage(formData);
            setNote((prev) => ({ ...prev, photoUrl: `${prev.photoUrl}?updated_at=${new Date().getTime()}` }));
            toastSuccess('Photo updated');
        } catch (error) {
            console.log(error);
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
    }, []);

    return (
        <>
            <Link to={'/notes'} className='link'><i className='bi bi-arrow-left'></i> Back to list</Link>
            <div className='profile'>
                <div className='profile__details'>
                    <img src={note.photoUrl} alt={`Profile photo of ${note.name}`} />
                    <div className='profile__metadata'>
                        <p className='profile__name'>{note.name}</p>
                        <p className='profile__muted'>JPG, GIF, or PNG. Max size of 10MG</p>
                        <button onClick={selectImage} className='btn'><i className='bi bi-cloud-upload'></i> Change Photo</button>
                    </div>
                </div>
                <div className='profile__settings'>
                    <div>
                        <form onSubmit={onUpdateNote} className="form">
                            <div className="user-details">
                                <input type="hidden" defaultValue={note.id} name="id" required />
                                <div className="input-box">
                                    <span className="details">Name</span>
                                    <input type="text" value={note.name} onChange={onChange} name="name" required />
                                </div>
                                <div className="input-box">
                                    <span className="details">Email</span>
                                    <input type="text" value={note.email} onChange={onChange} name="email" required />
                                </div>
                                <div className="input-box">
                                    <span className="details">Phone</span>
                                    <input type="text" value={note.phone} onChange={onChange} name="phone" required />
                                </div>
                                <div className="input-box">
                                    <span className="details">Address</span>
                                    <input type="text" value={note.address} onChange={onChange} name="address" required />
                                </div>
                                <div className="input-box">
                                    <span className="details">Title</span>
                                    <input type="text" value={note.title} onChange={onChange} name="title" required />
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
            </div>

            <form style={{ display: 'none' }}>
                <input type='file' ref={inputRef} onChange={(event) => udpatePhoto(event.target.files[0])} name='file' accept='image/*' />
            </form>
        </>
    )
}

export default NoteDetail;