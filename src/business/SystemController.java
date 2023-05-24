package business;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.TestData;
import dataaccess.User;
import librarysystem.panels.AddBookCopy;
import librarysystem.panels.SearchBook;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SystemController extends Component implements ControllerInterface {

    public static final SystemController INSTANCE = new SystemController();

    @Override
    public List<String[]> allMemberIds() {
        HashMap<String, LibraryMember> members;
        members = new DataAccessFacade().readMemberMap();
        List<String[]> table = new ArrayList<>();

        for (String k : members.keySet()) {
            String[] row = {members.get(k).getMemberId(), members.get(k).getFirstName(),
                    members.get(k).getLastName()};
            table.add(row);
        }
        return table;
    }

    @Override
    public List<String[]> allBookIds() {
        HashMap<String, Book> books = new DataAccessFacade().readBooksMap();
        List<String[]> table = new ArrayList<>();
        for (String k : books.keySet()) {
            String[] row = {books.get(k).getIsbn(), books.get(k).getTitle(), String.valueOf(books.get(k).getCopies().length)};
            table.add(row);
        }
        return table;
    }

    @Override
    public User login(String userName, String password) throws LoginException {
        TestData test = new TestData();
        List<User> users = test.allUsers;

        User u = null;
        for (User user : users) {
            if (userName.contains(user.getId()) && password.contains(user.getPassword())) {
                u = user;
                break;
            }
        }
        if (u == null)
            throw new LoginException("Username or password incorrect!");
        return u;
    }

    @Override
    public void addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors)
            throws LibrarySystemException {

        DataAccessFacade da = new DataAccessFacade();
        Book storedBook = da.searchBook(isbn);
        if (storedBook != null) {
            throw new LibrarySystemException("Book with ISBN " + isbn + " already exists");
        }
        Book book = new Book(isbn, title, maxCheckoutLength, authors);
        da.saveNewBook(book);
    }


    @Override
    public LibraryMember searchMember(String memberId) {
        DataAccessFacade da = new DataAccessFacade();
        da.searchMember(memberId);
        LibraryMember memeber = da.searchMember(memberId);
        if (memeber == null) {
            memberId = null;
        }
        return memeber;
    }

    @Override
    public void addMember(String id, String firstName, String lastName, String cell, String street, String city, String state, String zip) throws LibrarySystemException {
        if (id.length() == 0 || firstName.length() == 0 || lastName.length() == 0
                || cell.length() == 0 || street.length() == 0 || city.length() == 0
                || state.length() == 0 || zip.length() == 0) {
            throw new LibrarySystemException("All fields must be non-empty");
        }
        Address address = new Address(street, city, state, zip);
        if (searchMember(id) != null) {
            throw new LibrarySystemException("Library Member with ID " + id + " already exists");
        }
        DataAccess da = new DataAccessFacade();
        LibraryMember member = new LibraryMember(id, firstName, lastName, cell, address);
        da.saveNewMember(member);
    }

    public Book searchBook(String isbn) {
        DataAccessFacade daf = new DataAccessFacade();
        Book book = daf.searchBook(isbn);
        return book;
    }

    @Override
    public void updateBook(Book book) {
        DataAccessFacade daf = new DataAccessFacade();
        daf.updateBook(book);
    }

    @Override
    public boolean checkoutBook(Book checkBook, LibraryMember member, HashMap<String, LibraryMember> libMembers, DataAccessFacade da, HashMap<String, Book> books) {
        boolean flag = false;
        BookCopy[] bc = checkBook.getCopies();
        for (int i = 0; i < bc.length; i++) {
            if (bc[i].isAvailable()) {
                flag = true;
                LocalDate checkDate = LocalDate.now();
                LocalDate dueDate = checkDate.plusDays(checkBook.getMaxCheckoutLength());
                member.addCheckout(new CheckoutRecordEntry(checkBook, bc[i].getCopyNum(), checkDate, dueDate));
                bc[i].changeAvailability();
                da.saveMembersMap(libMembers);
                da.saveBooksMap(books);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validateAddBook(String bookIsbn, String bookAuthorFirstName, String bookAuthorLastName, String authorPhoneNumber, String bookTitle, String checkoutLengthData, String zipString, String streetString, String cityString, String stateString) {
        if (bookIsbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ISBN cannot be empty!!!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (bookAuthorFirstName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (bookAuthorLastName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Last Name cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (authorPhoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone Number cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (bookTitle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (streetString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Street cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (cityString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "City cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (stateString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "State cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (zipString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Zip cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


    @Override
    public boolean validateCheckoutBook(String memberId, String ISBN) {

        if (memberId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Member Id cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (ISBN.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ISBN Field cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            DataAccessFacade da = new DataAccessFacade();
            HashMap<String, LibraryMember> libMembers = da.readMemberMap();
            HashMap<String, Book> books = da.readBooksMap();
            LibraryMember member = libMembers.get(memberId);

            Book checkBook = books.get(ISBN);
            if (member == null) {
                System.out.println("Library member not found");
                JOptionPane.showMessageDialog(this, "Library member not found", "SUCESS",
                        JOptionPane.PLAIN_MESSAGE);
                return false;
            } else {
                if (checkBook == null) {
                    System.out.println("Book not found");
                    JOptionPane.showMessageDialog(this, "Book not found", "SUCESS",
                            JOptionPane.PLAIN_MESSAGE);
                    return false;
                } else {
                    boolean flag = checkoutBook(checkBook, member, libMembers, da, books);
                    if (!flag) {
                        System.out.println("No copies of book available");
                        JOptionPane.showMessageDialog(this, "No copies of book available",
                                "SUCCESS", JOptionPane.PLAIN_MESSAGE);
                        return false;
                    } else {
                        JOptionPane.showMessageDialog(this, "Checkout Book Successful", "SUCESS",
                                JOptionPane.PLAIN_MESSAGE);
                        return true;
                    }
                }
            }
        }
    }

    @Override
    public boolean validateLogin(String userName, String password) {
        if (userName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "UserName cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public boolean addCheckOutRecord(String memberID) {
        if (memberID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Member Id cannot be empty!!!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            LibraryMember library = new SystemController().searchMember(memberID);
            if (library == null) {
                JOptionPane.showMessageDialog(this, "Member Not Found", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                CheckoutRecord cr = library.getCheckoutRecord();

                if (cr == null) {
                    JOptionPane.showMessageDialog(this, "No checkout records found", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
                    return false;
                } else{
                    List<CheckoutRecordEntry> entries = cr.getEntries();
                    if (entries == null) {
                        JOptionPane.showMessageDialog(this, "No checkout records found", "SUCCESS",
                                JOptionPane.PLAIN_MESSAGE);
                        return false;
                    } else {
                        String msg = "";
                        for (CheckoutRecordEntry entry : entries) {
                            System.out.println(entry);
                            msg += entry + "\n";

                        }
                        JOptionPane.showMessageDialog(this, msg, "SUCCESS",
                                JOptionPane.PLAIN_MESSAGE);
                        return true;
                    }
                }
            }
        }
    }

    @Override
    public boolean addSearchBook(String bookID) {
        if (bookID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ISBN ID cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }else {
            Book book = searchBook(bookID);
            if (book == null) {
                JOptionPane.showMessageDialog(this, "Book Not Found", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                JOptionPane.showMessageDialog(this, book, "SUCCESS", JOptionPane.PLAIN_MESSAGE);
                return true;
            }
        }
    }

    @Override
    public boolean validateAddLibrayMember(String membId, String memberFirstName, String memberLastName, String telephone, String memberStreet, String memberCity, String memberState, String memberZip) {

        if (membId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Id cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (memberFirstName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "First Name cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (memberLastName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Last Name cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (telephone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone Number cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (memberStreet.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Street cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;

        } else if (memberCity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "City cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (memberState.isEmpty()) {
            JOptionPane.showMessageDialog(this, "State cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (memberZip.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Zip cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public boolean addBookCopy(String isbn) {

        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ISBN cannot be empty!!!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }else {
            Book book = searchBook(isbn);
            if (book == null) {
                JOptionPane.showMessageDialog(this, "Book not found!!!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                book.addCopy();
                updateBook(book);
                JOptionPane.showMessageDialog(this, "Book Copy Added", "SUCCESS",
                        JOptionPane.PLAIN_MESSAGE);
                return true;

            }
        }
    }
}
