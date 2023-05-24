package business;

import java.util.HashMap;
import java.util.List;

import dataaccess.DataAccessFacade;
import dataaccess.User;

public interface ControllerInterface {
    User login(String id, String password) throws LoginException;

    List<String[]> allMemberIds();

    List<String[]> allBookIds();

    void addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) throws LibrarySystemException;

    Book searchBook(String isbn);

    LibraryMember searchMember(String memberId);

    void updateBook(Book book);

    void addMember(String id, String firstName, String lastName, String cell, String street, String city, String state, String zip) throws LibrarySystemException;

    boolean checkoutBook(Book checkBook, LibraryMember member, HashMap<String, LibraryMember> libMembers, DataAccessFacade da, HashMap<String, Book> books);

    boolean validateAddBook(String bookIsbn, String bookAuthorFirstName, String bookAuthorLastName, String authorPhoneNumber, String bookTitle,
                            String checkoutLengthData, String zipString, String streetString, String cityString, String stateString);

    boolean validateCheckoutBook(String memberId, String ISBN);

    boolean validateLogin(String userName, String password);

    boolean addCheckOutRecord(String memberID);

    boolean addSearchBook(String bookID);

    boolean validateAddLibrayMember(String membId, String memberFirstName, String memberLastName, String telephone
    ,String memberStreet, String memberCity, String memberState, String memberZip);

    boolean addBookCopy(String isbn);

}
