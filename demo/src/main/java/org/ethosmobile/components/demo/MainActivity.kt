package org.ethosmobile.components.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.core.ui.DgenTheme
import org.ethosmobile.components.library.background.OpenGLBackground
import org.ethosmobile.components.library.opengl.view.OpenGLGlobeView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           DgenTheme {

               val context = LocalContext.current
               val glSurfaceView = remember {
                   OpenGLGlobeView(context)
               }

               OpenGLBackground(
                   globeRenderer = glSurfaceView,
                   globeColor = Color.Red
               ){

               }
           }
        }
    }
}



@Preview(showBackground = true, widthDp = 390, heightDp = 800)
@Composable
fun GreetingPreview() {

}