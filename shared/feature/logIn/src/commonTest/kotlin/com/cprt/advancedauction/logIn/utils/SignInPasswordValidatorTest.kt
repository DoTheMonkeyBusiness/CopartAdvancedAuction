import com.cprt.advancedauction.core.screen.exception.ValidationException
import com.cprt.advancedauction.core.screen.resources.appString.LoginErrorString
import com.cprt.advancedauction.logIn.utils.SignInPasswordValidator
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(MockKExtension::class)
internal class SignInPasswordValidatorTest {

    @RelaxedMockK
    private lateinit var loginErrorString: LoginErrorString

    @InjectMockKs
    private lateinit var passwordValidator: SignInPasswordValidator

    @ParameterizedTest
    @ValueSource(
        strings = [
            "2131",
            "1",
            "dsfds",
        ]
    )
    fun validPasswordTest(input: String) {
        assertDoesNotThrow { passwordValidator.validate(input) }
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "",
            "    ",
        ]
    )
    fun invalidPasswordTest(input: String) {
        assertThrows<ValidationException> { passwordValidator.validate(input) }
    }

}
