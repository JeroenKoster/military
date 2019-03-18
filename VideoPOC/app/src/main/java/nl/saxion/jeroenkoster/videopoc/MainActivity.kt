package nl.saxion.jeroenkoster.videopoc

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.util.Util.getUserAgent
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import java.net.URI


class MainActivity : AppCompatActivity() {

    lateinit var simplePlayer: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        simplePlayer = ExoPlayerFactory.newSimpleInstance(this@MainActivity)

        playerView.player = simplePlayer

        val uri = Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")

        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory = DefaultHttpDataSourceFactory(
            Util.getUserAgent(this@MainActivity, "VideoPOC")
        )
        // This is the MediaSource representing the media to be played.
        val videoSource = ExtractorMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
        // Prepare the player with the source.
        simplePlayer.prepare(videoSource)

        simplePlayer.playWhenReady
    }
}
