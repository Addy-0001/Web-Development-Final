import axios from "axios";

const API_URL = 'http://localhost:8080/notes';

export async function saveNote(note) {
    return await axios.post(API_URL, note);
}

export async function getNotes(page = 0, size = 10) {
    return await axios.get(`${API_URL}?page=${page}&size=${size}`);
}

export async function getNote(id) {
    return await axios.get(`${API_URL}/${id}`);
}

export async function udpateNote(note) {
    return await axios.post(API_URL, note);
}

export async function udpatePhoto(formData) {
    return await axios.put(`${API_URL}/photo`, formData);
}

export async function deleteNote(id) {
    return await axios.delete(`${API_URL}/${id}`);
}