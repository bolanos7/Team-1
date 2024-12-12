import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calmpulse.R

@Composable
fun SelectBreathingExerciseWithAudio(
    onBackClick: () -> Unit = {},
    onConfirmTrack: (Int?) -> Unit = {}, // Callback to confirm the selected track
    context: Context
) {
    val backgroundColor = Color(0xFFF5F5F5)
    val accentColor = Color(0xFFDBE681)
    val textColor = Color.Black

    val audioTracks = listOf(
        "Focus Audio" to R.raw.focus_audio,
        "Meditate Audio" to R.raw.meditate_audio,
        "Calm Audio" to R.raw.calm_audio,
        "Panic Audio" to R.raw.panic_audio
    )

    var selectedTrack by remember { mutableStateOf<Int?>(null) }
    var previewingTrack by remember { mutableStateOf<Int?>(null) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isPlayingPreview by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top bar
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = textColor)
            }

            // Title
            Text(
                text = "Select and Preview Breathing Mode",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Audio Tracks
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                audioTracks.forEach { (title, trackResId) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Track Title
                        Text(
                            text = title,
                            modifier = Modifier.weight(1f),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = textColor
                        )

                        // Preview Button
                        Button(
                            onClick = {
                                if (isPlayingPreview && previewingTrack == trackResId) {
                                    mediaPlayer?.stop()
                                    mediaPlayer?.release()
                                    mediaPlayer = null
                                    isPlayingPreview = false
                                    previewingTrack = null
                                } else {
                                    mediaPlayer?.stop()
                                    mediaPlayer?.release()
                                    previewingTrack = trackResId
                                    mediaPlayer = MediaPlayer.create(context, trackResId).apply {
                                        start()
                                    }
                                    isPlayingPreview = true
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (previewingTrack == trackResId) accentColor else Color.LightGray
                            )
                        ) {
                            Text(
                                text = if (previewingTrack == trackResId) "Stop" else "Preview",
                                color = textColor
                            )
                        }
                    }
                }
            }

            // Confirm Button
            Button(
                onClick = {
                    mediaPlayer?.stop()
                    mediaPlayer?.release()
                    mediaPlayer = null
                    isPlayingPreview = false
                    onConfirmTrack(previewingTrack) // Confirm the track selection
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                enabled = previewingTrack != null
            ) {
                Text(
                    text = "Confirm Track",
                    color = textColor,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSelectBreathingExerciseWithAudio() {

}
