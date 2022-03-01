package zdz.library.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zdz.library.compose.theme.Black

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 36.sp,
    textColor: Color = Black,
    fab: @Composable () -> Unit = {},
    fabPosition: FabPosition = FabPosition.End,
    content: @Composable (() -> Unit)? = null,
) {
    Title(
        title = {
            Text(text = text, fontSize = textSize, color = textColor)
        },
        modifier = modifier,
        fab = fab,
        fabPosition = fabPosition,
        content = content,
    )
}

/**
 * @param[title]可组合的标题槽位
 * @param[modifier]修饰符,修饰[content]所在的Column
 * @param[subtitle]可组合的副标题槽位,默认为空
 * @param[verticalArrangement]垂直排列方式
 * @param[horizontalAlignment]水平排列方式
 * @param[fab]悬浮按钮槽位//TODO: 其他分辨率未知
 * @param[fabPosition]悬浮按钮放置的位置
 * @param[content]中间的内容
 */
@Composable
fun Title(
    title: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    subtitle: @Composable (() -> Unit) = {},
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    fab: @Composable () -> Unit = {},
    fabPosition: FabPosition = FabPosition.End,
    content: @Composable (() -> Unit)? = null,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                title()
                subtitle()
            }
            content?.invoke()
        }
        Box(
            contentAlignment = if (fabPosition == FabPosition.End) Alignment.CenterEnd else Alignment.Center,
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        ) {
            fab()
        }
    }
}

@Suppress("INLINE_CLASS_DEPRECATED", "EXPERIMENTAL_FEATURE_WARNING")
inline class FabPosition internal constructor(@Suppress("unused") private val value: Int) {
    companion object {
        /**
         * Position FAB at the bottom of the screen in the center, above the [NavigationBar] (if it
         * exists)
         */
        val Center = FabPosition(0)
        
        /**
         * Position FAB at the bottom of the screen at the end, above the [NavigationBar] (if it
         * exists)
         */
        val End = FabPosition(1)
    }
    
    override fun toString(): String {
        return when (this) {
            Center -> "FabPosition.Center"
            else -> "FabPosition.End"
        }
    }
}