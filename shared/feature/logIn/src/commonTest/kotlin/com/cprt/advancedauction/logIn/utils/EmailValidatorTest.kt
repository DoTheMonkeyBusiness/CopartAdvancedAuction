import com.cprt.advancedauction.core.exception.ValidationException
import com.cprt.advancedauction.core.resources.appString.LoginErrorString
import com.cprt.advancedauction.logIn.utils.EmailValidator
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(MockKExtension::class)
internal class EmailValidatorTest {

    @RelaxedMockK
    private lateinit var loginErrorString: LoginErrorString

    @InjectMockKs
    private lateinit var emailValidator: EmailValidator

    @ParameterizedTest
    @ValueSource(
        strings = [
            "abc@gmail.com",
            "abc@icloud.com",
            "abc@mail.ru",
            "abc@customdomain.com",
            "caseCheck@gmail.com",
            "CaseCheck@gmail.com",
            "dots.check@gmail.com",
        ]
    )
    fun validEmailTest(input: String) {
        assertDoesNotThrow { emailValidator.validate(input) }
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "",
            "    ",
            "русскиесимволы@gmail.com",
            "русскиесимволы@gmail.com",
            "whothoutAtgmail.com",
        ]
    )
    fun invalidEmailTest(input: String) {
        assertThrows<ValidationException> { emailValidator.validate(input) }
    }
}
