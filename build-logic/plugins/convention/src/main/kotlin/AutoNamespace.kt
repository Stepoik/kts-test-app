import org.gradle.api.Project
import java.util.Locale

fun Project.autoNamespace(): String {
    val base = (findProperty("baseNamespace") as? String)?.trim().orEmpty()
        .ifEmpty { "ru.stepan" }

    // ":features:auth:ui" -> ["features","auth","ui"]
    val segments = path
        .split(':')
        .filter { it.isNotBlank() }
        .map { it.toNamespaceSegment() }

    return (listOf(base) + segments).joinToString(".")
}

private fun String.toNamespaceSegment(): String {
    // Разрешаем только [a-z0-9_], всё остальное в "_"
    var s = lowercase(Locale.US)
        .replace(Regex("[^a-z0-9_]"), "_")
        .replace(Regex("_+"), "_")
        .trim('_')

    // namespace сегмент не может начинаться с цифры
    if (s.isNotEmpty() && s[0].isDigit()) s = "_$s"

    // на всякий случай, если сегмент пустой после очистки
    if (s.isBlank()) s = "m"

    return s
}