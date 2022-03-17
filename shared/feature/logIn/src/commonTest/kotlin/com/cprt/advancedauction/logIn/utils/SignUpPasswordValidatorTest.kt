import com.cprt.advancedauction.core.screen.exception.ValidationException
import com.cprt.advancedauction.core.screen.resources.appString.LoginErrorString
import com.cprt.advancedauction.logIn.utils.SignUpPasswordValidator
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@ExtendWith(MockKExtension::class)
internal class SignUpPasswordValidatorTest {

    @RelaxedMockK
    private lateinit var loginErrorString: LoginErrorString

    @InjectMockKs
    private lateinit var passwordValidator: SignUpPasswordValidator

    @ParameterizedTest
    @CsvSource(
        "123456, 123456",
        "abcdef, abcdef",
    )
    fun validPasswordTest(
        password: String,
        repeatPassword: String
    ) {
        val passwords = password to repeatPassword

        assertDoesNotThrow { passwordValidator.validate(passwords) }
    }

    @ParameterizedTest
    @CsvSource(
        "'', 1234567",
        "'    ', 1234567",
        "1234567, ''",
        "1234567, '      '",
        "123, 123",
        "abc, abc",
        "abcd, abc",
        "abc, abcd",
    )
    fun invalidPasswordTest(
        password: String,
        repeatPassword: String
    ) {
        val passwords = password to repeatPassword

        assertThrows<ValidationException> { passwordValidator.validate(passwords) }
    }
}
