[中文文档](https://blog.csdn.net/qq_33505109/article/details/125194044)

# load-the-image

load-the-image Apply to compose-jb(desktop), Used to load network and local pictures.

<h1>Mode of use</h1>
Root dir, settings.gradle add:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

build.gradle add:

```
repositories {
    	...
    	maven { url 'https://jitpack.io' }
}
```

<h3>If you just use compose-desktop</h3>

version = [![](https://jitpack.io/v/ltttttttttttt/load-the-image.svg)](https://jitpack.io/#ltttttttttttt/load-the-image)

Your compose-desktop dir, build.gradle add:

```
dependencies {
	implementation 'com.github.ltttttttttttt:load-the-image:$version'
}
```

Use the code load image with network and file and resources

```
Image(rememberImagePainter(/*url*/"https://img.zcool.cn/community/017e625e57415ea801216518c25819.jpg@1280w_1l_2o_100sh.jpg","")
```

<h3>If you use compose(Kotlin Multiplatform), You can refer to the example.</h3>

version = [![](https://jitpack.io/v/ltttttttttttt/load-the-image.svg)](https://jitpack.io/#ltttttttttttt/load-the-image)

Your common dir, build.gradle add:

```
val desktopMain by getting{
	dependencies {
		implementation 'com.github.ltttttttttttt:load-the-image:$version'
	}
}
```

commonMain add function:

```
@Composable
expect fun rememberImagePainter(url: String): Painter
```

androidMain add function(and other target):

```
@Composable
actual fun rememberImagePainter(url: String): Painter =
    coil.compose.rememberImagePainter(data = url)
```

desktopMain add function:

```
@Composable
actual fun rememberImagePainter(url: String): Painter =
    com.lt.load_the_image.rememberImagePainter(url)
    
```
Use the code load image with network and file and resources

```
Image(rememberImagePainter(/*url*/"https://img.zcool.cn/community/017e625e57415ea801216518c25819.jpg@1280w_1l_2o_100sh.jpg","")
```
