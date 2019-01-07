package xiaofu.lib.base.bind

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import xiaofu.lib.inline.loadUrl

/**
 *
 * Created by @author xiaofu on 2019/1/7.
 */
object LoadImageAdapter {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String) {
        imageView.loadUrl(imageView.context, url)
    }

}