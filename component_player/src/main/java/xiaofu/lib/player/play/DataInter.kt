package xiaofu.lib.player.play

import com.kk.taurus.playerbase.assist.InterEvent
import com.kk.taurus.playerbase.assist.InterKey

interface DataInter {

    interface Event : InterEvent {
        companion object {
            const val EVENT_MULTIPLE_PLAY = -1001
            const val EVENT_CODE_REQUEST_BACK = -100
            const val EVENT_CODE_REQUEST_CLOSE = -101
            const val EVENT_CODE_REQUEST_TOGGLE_SCREEN = -104
            const val EVENT_CODE_ERROR_SHOW = -111
        }

    }

    interface Key : InterKey {
        companion object {
            const val KEY_MULTIPLE_PLAY = "multiple_play"

            const val KEY_IS_LANDSCAPE = "isLandscape"

            const val KEY_DATA_SOURCE = "data_source"

            const val KEY_ERROR_SHOW = "error_show"

            const val KEY_COMPLETE_SHOW = "complete_show"
            const val KEY_CONTROLLER_TOP_ENABLE = "controller_top_enable"
            const val KEY_CONTROLLER_SCREEN_SWITCH_ENABLE = "screen_switch_enable"

            const val KEY_TIMER_UPDATE_ENABLE = "timer_update_enable"

            const val KEY_NETWORK_RESOURCE = "network_resource"
        }

    }

    interface ReceiverKey {
        companion object {
            const val KEY_LOADING_COVER = "loading_cover"
            const val KEY_CONTROLLER_COVER = "controller_cover"
            const val KEY_GESTURE_COVER = "gesture_cover"
            const val KEY_COMPLETE_COVER = "complete_cover"
            const val KEY_ERROR_COVER = "error_cover"
            const val KEY_CLOSE_COVER = "close_cover"
        }
    }

    interface PrivateEvent {
        companion object {
            const val EVENT_CODE_UPDATE_SEEK = -201
        }
    }

}