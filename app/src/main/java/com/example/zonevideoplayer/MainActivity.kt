package com.example.zonevideoplayer

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.REPEAT_MODE_ONE
import androidx.media3.common.Tracks
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.zonevideoplayer.ui.theme.ZoneVideoPlayerTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZoneVideoPlayerTheme { // A surface container using the 'background' color from the theme

                val player = ExoPlayer.Builder(this).build()
                val uri = Uri.parse("asset:///1.mp4")
                val media = MediaItem.fromUri(uri)
                player.setMediaItem(media)
                player.prepare()
                player.repeatMode = REPEAT_MODE_ONE

                player.play()

                player.addListener(object : Player.Listener {
                    override fun onTracksChanged(tracks: Tracks) { // Update UI using current tracks.
                    }
                })

                Column(
                    modifier = Modifier.fillMaxSize(), //.padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    AndroidView(factory = { context ->
                        PlayerView(context).apply {
                            this.player = player
                            this.useController = false
                        }
                    }, update = { }, modifier = Modifier.fillMaxWidth() //.aspectRatio(16 / 9f)
                    )
                }
            }
        }
    }
}

