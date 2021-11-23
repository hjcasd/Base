package com.hjc.module_home.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjc.library_common.router.path.RouteHomePath
import com.hjc.module_home.R
import com.hjc.module_home.ui.compose.ui.theme.BaseTheme

/**
 * @Author: HJC
 * @Date: 2021/11/23 9:16
 * @Description: Compose
 */
@Route(path = RouteHomePath.URL_COMPOSE)
class ComposeTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                PreviewMessageCard()
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Surface(
        shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
        elevation = 5.dp,
        modifier = Modifier.padding(all = 8.dp)
    ) {
        Row(modifier = Modifier.padding(all = 10.dp)) {
            Image(
                painter = painterResource(id = R.mipmap.base_img_example),
                contentDescription = "111",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp, MaterialTheme.colors.secondary, shape = CircleShape
                    ),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Column() {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondaryVariant
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                Text(text = msg.body)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMessageCard() {
    MessageCard(msg = Message("哈哈哈", "呵呵呵呵呵呵"))
}

