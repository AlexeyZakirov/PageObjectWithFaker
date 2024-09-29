import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomUtils;


public class RegistrationWithFakerTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    RandomUtils randomUtils = new RandomUtils();

    String firstName = randomUtils.getFirstName();
    String lastName = randomUtils.getLastName();
    String email = randomUtils.getEmail();
    String phoneNumber = randomUtils.getNumber();
    String studentGender = randomUtils.getStudentGender();
    String month = randomUtils.getMonth();
    String year = randomUtils.getYear();
    int day = randomUtils.getDay();
    String subject = randomUtils.getSubject();
    String hobby = randomUtils.getHobbies();
    String pathToPicture = randomUtils.getImage();
    String currentAddress = randomUtils.getAddress();
    String state = randomUtils.getState();
    String city = randomUtils.getCity(state);
    String resultName = randomUtils.getResultName(firstName, lastName);
    String resultBirthday = randomUtils.getResultBirthday(year, month, day);
    String resultStateAndCity = randomUtils.getResultStateAndCity(state, city);
    String invalidStudentNumber = randomUtils.getInvalidNumber();


    @Test
    public void fillAllFieldsTest() {
        registrationPage.openPage()
                .setStudentName(firstName, lastName)
                .setEmail(email)
                .setStudentGenderWrapper(studentGender)
                .setStudentNumber(phoneNumber)
                .setDateOfBirth(month, year, day)
                .setSubjects(subject)
                .setHobby(hobby)
                .uploadPicture(pathToPicture)
                .setCurrentAddressInput(currentAddress)
                .setStateAndCity(state, city)
                .submitRegistration()

                .checkResultDialogIsAppear()
                .checkRegistrationResult("Student Name", resultName)
                .checkRegistrationResult("Student Email", email)
                .checkRegistrationResult("Gender", studentGender)
                .checkRegistrationResult("Mobile", phoneNumber)
                .checkRegistrationResult("Date of Birth", resultBirthday)
                .checkRegistrationResult("Subjects", subject)
                .checkRegistrationResult("Hobbies", hobby)
                .checkRegistrationResult("Picture", pathToPicture)
                .checkRegistrationResult("Address", currentAddress)
                .checkRegistrationResult("State and City", resultStateAndCity);
    }

    @Test
    public void fillMinimumFieldsTest() {
        registrationPage.openPage()
                .setStudentName(firstName, lastName)
                .setEmail(email)
                .setStudentGenderWrapper(studentGender)
                .setStudentNumber(phoneNumber)
                .setDateOfBirth(month, year, day)
                .submitRegistration()

                .checkResultDialogIsAppear()
                .checkRegistrationResult("Student Name", resultName)
                .checkRegistrationResult("Student Email", email)
                .checkRegistrationResult("Gender", studentGender)
                .checkRegistrationResult("Mobile", phoneNumber)
                .checkRegistrationResult("Date of Birth", resultBirthday);
    }

    @Test
    public void fillMinimumFieldsWithNonNumericNumberTest() {
        registrationPage.openPage()
                .setStudentName(firstName, lastName)
                .setEmail(email)
                .setStudentGenderWrapper(studentGender)
                .setStudentNumber(invalidStudentNumber)
                .setDateOfBirth(month, year, day)
                .submitRegistration()

                .checkResultIsNotAppear();
    }

}
