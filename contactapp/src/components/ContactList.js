import React from 'react';
import Contact from "./Contact"

const ContactList = ({ contactData, currentContactPage, getAllContacts }) => {
    console.log(contactData)
    
    return (
        <main className='main'>
            {contactData?.content?.length === 0 && <div>No Contacts. Please add a new contact</div>}

            <ul className='contact__list'>
                {contactData?.content?.length > 0 && contactData.content.map(contact => (
                    <Contact contact={contact} key={contact.id} />
                ))}
            </ul>

            {contactData?.content?.length > 0 && contactData?.totalPages > 1 &&
                <div className='pagination'>
                    <a
                        onClick={() => getAllContacts(currentContactPage - 1)}
                        className={currentContactPage === 0 ? 'disabled' : ''}
                    >
                        &laquo;
                    </a>

                    {[...Array(contactData.totalPages).keys()].map(page => (
                        <a
                            onClick={() => getAllContacts(page)}
                            className={currentContactPage === page ? 'active' : ''}
                            key={page}
                        >
                            {page + 1}
                        </a>
                    ))}

                    <a
                        onClick={() => getAllContacts(currentContactPage + 1)}
                        className={contactData.totalPages === currentContactPage + 1 ? 'disabled' : ''}
                    >
                        &raquo;
                    </a>
                </div>
            }
        </main>
    )
}

export default ContactList;
