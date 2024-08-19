import { useEffect, useRef, useState } from 'react';
import 'react-toastify/dist/ReactToastify.css';
import Header from './components/Header';
import ContactList from './components/ContactList';
import { getContacts, saveContact, updateContactPhoto } from './api/ContactService';
import { getNotes, saveNote, updateNotePhoto } from './api/NoteService';
import { Routes, Route, Navigate, useNavigate } from 'react-router-dom';
import ContactDetail from './components/ContactDetail';
import { toastError } from './api/ToastService';
import { ToastContainer } from 'react-toastify';
import NoteList from './components/NoteList';
import NoteDetail from './components/NoteDetail';

function App() {
  const navigate = useNavigate();
  const contactmodalRef = useRef();
  const contactfileRef = useRef();
  const [contactData, setContactData] = useState({});
  const [currentContactPage, setCurrentContactPage] = useState(0);
  const [contactFile, setContactFile] = useState(undefined);
  const [contactValues, setContactValues] = useState({
    name: '',
    email: '',
    phone: '',
    address: '',
    title: '',
    status: '',
  });

  const getAllContacts = async (page = 0, size = 10) => {
    try {
      setCurrentContactPage(page);
      const { contactData } = await getContacts(page, size);
      setContactData(contactData);
      console.log(contactData);
    } catch (error) {
      console.log(error);
      toastError(error.message);
    }
  };

  const onChange = (event) => {
    setContactValues({ ...contactValues, [event.target.name]: event.target.value });
  };

  const handleNewContact = async (event) => {
    event.preventDefault();
    try {
      const { contactData } = await saveContact(contactValues);
      const formData = new FormData();
      formData.append('contactFile', contactFile, contactFile.name);
      formData.append('id', contactData.id);
      await updateContactPhoto(formData);
      toggleContactModal(false);
      setContactFile(undefined);
      contactfileRef.current.value = null;
      setContactValues({
        name: '',
        email: '',
        phone: '',
        address: '',
        title: '',
        status: '',
      });
      getAllContacts();
    } catch (error) {
      console.log(error);
      toastError(error.message);
    }
  };

  const updateContact = async (contact) => {
    try {
      await saveContact(contact);
    } catch (error) {
      console.log(error);
      toastError(error.message);
    }
  };

  const updateContactImage = async (formData) => {
    try {
      await updateContactPhoto(formData);
    } catch (error) {
      console.log(error);
      toastError(error.message);
    }
  };

  const toggleContactModal = show => show ? contactmodalRef.current.showModal() : contactmodalRef.current.close();

  useEffect(() => {
    getAllContacts();
  }, []);

  // Note
  const notemodalRef = useRef();
  const notefileRef = useRef();
  const [noteData, setNoteData] = useState({});
  const [currentNotePage, setCurrentNotePage] = useState(0);
  const [noteFile, setNoteFile] = useState(undefined);
  const [noteValues, setNoteValues] = useState({
    title: '',
    content: '',
    status: '',
  });

  const getAllNotes = async (page = 0, size = 10) => {
    try {
      setCurrentNotePage(page);
      const { noteData } = await getNotes(page, size);
      setNoteData(noteData);
      console.log(noteData);
    } catch (error) {
      console.log(error);
      toastError(error.message);
    }
  };

  const handleNewNote = async (event) => {
    event.preventDefault();
    try {
      const { noteData } = await saveNote(noteValues);
      const formData = new FormData();
      formData.append('noteFile', noteFile, noteFile.name);
      formData.append('id', noteData.id);
      await updateNotePhoto(formData);
      toggleNoteModal(false);
      setNoteFile(undefined);
      notefileRef.current.value = null;
      setNoteValues({
        title: '',
        content: '',
        status: '',
      });
      getAllNotes();
    } catch (error) {
      console.log(error);
      toastError(error.message);
    }
  };

  const updateNote = async (note) => {
    try {
      await saveNote(note);
    } catch (error) {
      console.log(error);
      toastError(error.message);
    }
  };

  const updateNoteImage = async (formData) => {
    try {
      await updateNotePhoto(formData);
    } catch (error) {
      console.log(error);
      toastError(error.message);
    }
  };

  const toggleNoteModal = show => show ? notemodalRef.current.showModal() : notemodalRef.current.close();

  useEffect(() => {
    getAllNotes();
  }, []);

  return (
    <>
      <Header toggleContactModal={toggleContactModal} toggleNoteModal={toggleNoteModal} nbOfContacts={contactData?.totalElements || 0} nbOfNotes={noteData?.totalElements || 0} />
      <main className='main'>
        <div className='container'>
          <Routes>
            <Route path='/' element={<Navigate to={'/contacts'} />} />
            <Route path="/contacts" element={<ContactList contactData={contactData} currentContactPage={currentContactPage} getAllContacts={getAllContacts} />} />
            <Route path="/contacts/:id" element={<ContactDetail updateContact={updateContact} updateContactImage={updateContactImage} />} />
            <Route path="/notes" element={<NoteList noteData={noteData} currentNotePage={currentNotePage} getAllNotes={getAllNotes} />} />
            <Route path="/notes/:id" element={<NoteDetail updateNote={updateNote} updateNoteImage={updateNoteImage} />} />
          </Routes>
        </div>
      </main>

      {/* Modal for Contact */}
      <dialog ref={contactmodalRef} className="modal" id="contactModal">
        <div className="modal__header">
          <h3>New Contact</h3>
          <i onClick={() => toggleContactModal(false)} className="bi bi-x-lg"></i>
        </div>
        <div className="divider"></div>
        <div className="modal__body">
          <form onSubmit={handleNewContact}>
            <div className="user-details">
              <div className="input-box">
                <span className="details">Name</span>
                <input type="text" value={contactValues.name} onChange={onChange} name='name' required />
              </div>
              <div className="input-box">
                <span className="details">Email</span>
                <input type="text" value={contactValues.email} onChange={onChange} name='email' required />
              </div>
              <div className="input-box">
                <span className="details">Title</span>
                <input type="text" value={contactValues.title} onChange={onChange} name='title' required />
              </div>
              <div className="input-box">
                <span className="details">Phone Number</span>
                <input type="text" value={contactValues.phone} onChange={onChange} name='phone' required />
              </div>
              <div className="input-box">
                <span className="details">Address</span>
                <input type="text" value={contactValues.address} onChange={onChange} name='address' required />
              </div>
              <div className="input-box">
                <span className="details">Account Status</span>
                <input type="text" value={contactValues.status} onChange={onChange} name='status' required />
              </div>
              <div className="contactFile-input">
                <span className="details">Profile Photo</span>
                <input type="file" onChange={(event) => setContactFile(event.target.files[0])} ref={contactfileRef} name='photo' required />
              </div>
            </div>
            <div className="form_footer">
              <button onClick={() => toggleContactModal(false)} type='button' className="btn btn-danger">Cancel</button>
              <button type='submit' className="btn">Save</button>
            </div>
          </form>
        </div>
      </dialog>

      {/* Modal for Note */}
      <dialog ref={notemodalRef} className="modal" id="noteModal">
        <div className="modal__header">
          <h3>New Note</h3>
          <i onClick={() => toggleNoteModal(false)} className="bi bi-x-lg"></i>
        </div>
        <div className="divider"></div>
        <div className="modal__body">
          <form onSubmit={handleNewNote}>
            <div className="user-details">
              <div className="input-box">
                <span className="details">Title</span>
                <input type="text" value={noteValues.title} onChange={onChange} name='title' required />
              </div>
              <div className="input-box">
                <span className="details">Content</span>
                <input type="text" value={noteValues.content} onChange={onChange} name='content' required />
              </div>
              <div className="input-box">
                <span className="details">Status</span>
                <input type="text" value={noteValues.status} onChange={onChange} name='status' required />
              </div>
              <div className="noteFile-input">
                <span className="details">Note Photo</span>
                <input type="file" onChange={(event) => setNoteFile(event.target.files[0])} ref={notefileRef} name='photo' required />
              </div>
            </div>
            <div className="form_footer">
              <button onClick={() => toggleNoteModal(false)} type='button' className="btn btn-danger">Cancel</button>
              <button type='submit' className="btn">Save</button>
            </div>
          </form>
        </div>
      </dialog>

      <ToastContainer />
    </>
  );
}

export default App;
