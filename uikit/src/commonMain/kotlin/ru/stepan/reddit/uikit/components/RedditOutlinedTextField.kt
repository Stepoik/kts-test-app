package ru.stepan.reddit.uikit.components

import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField as MaterialTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.stepan.reddit.core.ui.compose.SerializableTextFieldValue
import ru.stepan.reddit.core.ui.compose.serializable

@Composable
fun RedditOutlinedTextField(
    value: SerializableTextFieldValue,
    onValueChange: (SerializableTextFieldValue) -> Unit,
    placeholder: String? = null,
    modifier: Modifier = Modifier
) {
    MaterialTextField(
        value = value.textFieldValue,
        onValueChange = { onValueChange(it.serializable()) },
        placeholder = placeholder?.let { { Text(placeholder) } },
        modifier = modifier
    )
}