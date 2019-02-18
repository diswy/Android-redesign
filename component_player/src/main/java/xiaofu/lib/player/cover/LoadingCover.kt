package xiaofu.lib.player.cover

import android.content.Context
import android.os.Bundle
import android.view.View
import com.kk.taurus.playerbase.event.OnPlayerEventListener
import com.kk.taurus.playerbase.player.IPlayer
import com.kk.taurus.playerbase.receiver.BaseCover
import com.kk.taurus.playerbase.receiver.PlayerStateGetter
import xiaofu.component.player.R

class LoadingCover constructor(context: Context) : BaseCover(context) {

    override fun onCoverAttachedToWindow() {
        super.onCoverAttachedToWindow()
        val playerStateGetter = playerStateGetter
        if (playerStateGetter != null && isInPlaybackState(playerStateGetter)) {
            setLoadingState(playerStateGetter.isBuffering)
        }
    }

    private fun isInPlaybackState(playerStateGetter: PlayerStateGetter): Boolean {
        val state = playerStateGetter.state
        return (state != IPlayer.STATE_END
                && state != IPlayer.STATE_ERROR
                && state != IPlayer.STATE_IDLE
                && state != IPlayer.STATE_INITIALIZED
                && state != IPlayer.STATE_STOPPED)
    }

    private fun setLoadingState(show: Boolean) {
        setCoverVisibility(if (show) View.VISIBLE else View.GONE)
    }

    override fun onPlayerEvent(eventCode: Int, bundle: Bundle?) {
        when (eventCode) {
            OnPlayerEventListener.PLAYER_EVENT_ON_BUFFERING_START,
            OnPlayerEventListener.PLAYER_EVENT_ON_DATA_SOURCE_SET,
            OnPlayerEventListener.PLAYER_EVENT_ON_PROVIDER_DATA_START,
            OnPlayerEventListener.PLAYER_EVENT_ON_SEEK_TO -> setLoadingState(true)
            OnPlayerEventListener.PLAYER_EVENT_ON_VIDEO_RENDER_START,
            OnPlayerEventListener.PLAYER_EVENT_ON_BUFFERING_END,
            OnPlayerEventListener.PLAYER_EVENT_ON_STOP,
            OnPlayerEventListener.PLAYER_EVENT_ON_PROVIDER_DATA_ERROR,
            OnPlayerEventListener.PLAYER_EVENT_ON_SEEK_COMPLETE -> setLoadingState(false)
        }
    }

    override fun onReceiverEvent(eventCode: Int, bundle: Bundle?) {

    }

    override fun onErrorEvent(eventCode: Int, bundle: Bundle?) {
        setLoadingState(false)
    }

    override fun onCreateCoverView(context: Context?): View {
        return View.inflate(context, R.layout.layout_loading_cover,null)
    }

    override fun getCoverLevel(): Int {
        return levelMedium(1)
    }
}