package com.cprt.advancedauction.vehicleSearch.foundation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cprt.advancedauction.core.utils.throttleFirst
import com.cprt.advancedauction.foundation.AADivider
import com.cprt.advancedauction.foundation.textfield.AAOutlinedTextField

@Composable
internal fun FilterableList(
    modifier: Modifier = Modifier,
    elements: List<String>,
    selectedElement: String,
    onElementSelected: (String) -> Unit,
    filterText: TextFieldValue,
    onTextValueChange: (TextFieldValue) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val onFilterChanged: (TextFieldValue) -> Unit = throttleFirst(
        coroutineScope = coroutineScope,
    ) {
        onTextValueChange(it)
    }

    AAOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        value = filterText,
        onValueChange = onFilterChanged,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
            )
        }
    )
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 8.dp)
    ) {
        items(elements) { text ->
            SelectionSell(
                value = text,
                isSelected = selectedElement == text,
                onClick = { onElementSelected(text) },
            )
            AADivider()
        }
    }
}

@Composable
private fun SelectionSell(
    modifier: Modifier = Modifier,
    value: String,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .selectable(
                selected = isSelected,
                onClick = onClick
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Icon(
            modifier = Modifier.alpha(if (isSelected) 1f else 0f),
            imageVector = Icons.Default.Check,
            contentDescription = null,
        )
    }
}
