package extension

import java.util.*

fun Properties.getString(key: String) = requireNotNull(this[key] as? String) {
    "Property $key not defined"
}
