package com.example.alertdialogalvaroguerreroduarte

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp

@Composable
fun AlertDialogsApp() {
    var texto1 by remember { mutableStateOf("Texto Principal") }
    var showDialog by remember { mutableStateOf<DialogType?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = texto1, fontSize = 20.sp, modifier = Modifier.padding(16.dp))

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { showDialog = DialogType.Confirmation }) {
            Text("Confirmación de Accion")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { showDialog = DialogType.Deletion }) {
            Text("Eliminar Elemento")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { showDialog = DialogType.Information }) {
            Text("Aviso Importante")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { showDialog = DialogType.Authentication }) {
            Text("Requiere Auntenticacion")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { showDialog = DialogType.CriticalError }) {
            Text("Error Crítico")
        }

        when (showDialog) {
            DialogType.Confirmation -> ConfirmationDialog { confirmed ->
                if (confirmed) texto1 = "Acción Confirmada"
                showDialog = null
            }
            DialogType.Deletion -> DeletionDialog { borrado ->
                if (borrado) texto1 = "elemento eliminado exitosamente"
                showDialog = null
            }
            DialogType.Information -> InformationDialog { showDialog = null }
            DialogType.Authentication -> AuthenticationDialog { coonfirmado ->
                if (coonfirmado) texto1 = "Usuario Autenticado"
                showDialog = null
            }
            DialogType.CriticalError -> CriticalErrorDialog { reintentar ->
                if (reintentar) texto1 = "Intento de Reintento"
                showDialog = null
            }
            null -> {}
        }
    }
}


enum class DialogType {
    Confirmation, Deletion, Information, Authentication, CriticalError
}

@Composable
fun ConfirmationDialog(onResult: (Boolean) -> Unit) {
    AlertDialog(
        onDismissRequest = { onResult(false) },
        title = { Text("Confirmación de Acción") },
        text = { Text("¿Estás seguro de que deseas continuar con esta acción?") },
        confirmButton = {
            Button(onClick = { onResult(true) }) { Text("Sí") }
        },
        dismissButton = {
            Button(onClick = { onResult(false) }) { Text("No") }
        }
    )
}


@Composable
fun DeletionDialog(onResult: (Boolean) -> Unit) {
    AlertDialog(
        onDismissRequest = { onResult(false) },
        title = { Text("Eliminar Elemento") },
        text = { Text("Esta acción es irreversible. ¿Deseas eliminar este elemento?") },
        confirmButton = {
            Button(onClick = { onResult(true) }) { Text("Eliminar") }
        },
        dismissButton = {
            Button(onClick = { onResult(false) }) { Text("Cancelar") }
        }
    )
}


@Composable
fun InformationDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Aviso Importante") },
        text = { Text("Recuerda que los cambios realizados no se pueden deshacer.") },
        confirmButton = {
            Button(onClick = onDismiss) { Text("Entendido") }
        }
    )
}

@Composable
fun AuthenticationDialog(onResult: (Boolean) -> Unit) {
    AlertDialog(
        onDismissRequest = { onResult(false) },
        title = { Text("Requiere Autenticación") },
        text = { Text("Para continuar, necesitas autenticarte de nuevo.") },
        confirmButton = {
            Button(onClick = { onResult(true) }) { Text("Autenticar") }
        },
        dismissButton = {
            Button(onClick = { onResult(false) }) { Text("Cancelar") }
        }
    )
}

@Composable
fun CriticalErrorDialog(onResult: (Boolean) -> Unit) {
    AlertDialog(
        onDismissRequest = { onResult(false) },
        title = { Text("Error Crítico") },
        text = { Text("Se ha producido un error crítico. ¿Deseas intentar nuevamente?") },
        confirmButton = {
            Button(onClick = { onResult(true) }) { Text("Reintentar") }
        },
        dismissButton = {
            Button(onClick = { onResult(false) }) { Text("Cancelar") }
        }
    )
}