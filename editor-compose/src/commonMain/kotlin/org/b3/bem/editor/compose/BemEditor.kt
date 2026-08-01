package org.b3.bem.editor.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BemEditor() {
    var resourceName by remember { mutableStateOf("") }
    var equipmentName by remember { mutableStateOf("") }

    var wh by remember { mutableStateOf(false) }
    var kwh by remember { mutableStateOf(false) }

    var electric by remember { mutableStateOf(false) }
    var water by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        Text("Resources")

        OutlinedTextField(
            value = resourceName,
            onValueChange = { resourceName = it },
            label = { Text("Resource Name") }
        )

        Row {
            Checkbox(
                checked = wh,
                onCheckedChange = { wh = it },
            )
            Text("wh")
        }

        Row {
            Checkbox(
                checked = kwh,
                onCheckedChange = { kwh = it },
            )
            Text("kwh")
        }

        Button(
            onClick = {},
        ) {
            Text("Add Resource")
        }

        Spacer(Modifier.height(32.dp))

        Text("Equipments")

        OutlinedTextField(
            value = equipmentName,
            onValueChange = { equipmentName = it },
            label = { Text("Equipment name") },
        )

        Row {
            Checkbox(
                checked = electric,
                onCheckedChange = { electric = it },
            )
            Text("electric")
        }

        Row {
            Checkbox(
                checked = water,
                onCheckedChange = { water = it },
            )
            Text("water")
        }

        Button(
            onClick = {
                // TODO
            },
        ) {
            Text("Add Equipment")
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {
                // TODO Export YAML
            },
        ) {
            Text("Export YAML")
        }
    }
}
