package ru.stepan.reddit.uikit.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField as MaterialTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.VisualTransformation.Companion
import ru.stepan.reddit.core.ui.compose.SerializableTextFieldValue
import ru.stepan.reddit.core.ui.compose.serializable

@Composable
fun RedditOutlinedTextField(
    value: SerializableTextFieldValue,
    onValueChange: (SerializableTextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    singleLine: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    MaterialTextField(
        value = value.textFieldValue,
        onValueChange = { onValueChange(it.serializable()) },
        placeholder = placeholder?.let { { Text(placeholder) } },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        modifier = modifier
    )
}