package org.m0skit0.android.mapswrapper

class MarkerOptions {

    private var google = com.google.android.gms.maps.model.MarkerOptions()
    private var huawei = com.huawei.hms.maps.model.MarkerOptions()

    fun position(position: LatLng): MarkerOptions = apply {
        google = google.position(position.google)
        huawei = huawei.position(position.huawei)
    }

    fun zIndex(zIndex: Float): MarkerOptions = apply {
        google = google.zIndex(zIndex)
        huawei = huawei.zIndex(zIndex)
    }

    fun anchor(x: Float, y: Float): MarkerOptions = apply {
        google = google.anchor(x, y)
        huawei = huawei.anchor(x, y)
    }

    fun infoWindowAnchor(x: Float, y: Float): MarkerOptions = apply {
        google = google.infoWindowAnchor(x, y)
        huawei = huawei.infoWindowAnchor(x, y)
    }

    fun title(title: String): MarkerOptions = apply {
        google = google.title(title)
        huawei = huawei.title(title)
    }

    fun snippet(snippet: String): MarkerOptions = apply {
        google = google.snippet(snippet)
        huawei = huawei.snippet(snippet)
    }

    fun draggable(draggable: Boolean): MarkerOptions = apply {
        google = google.draggable(draggable)
        huawei = huawei.draggable(draggable)
    }

    fun visible(visible: Boolean): MarkerOptions = apply {
        google = google.visible(visible)
        huawei = huawei.visible(visible)
    }

    fun flat(flat: Boolean): MarkerOptions = apply {
        google = google.flat(flat)
        huawei = huawei.flat(flat)
    }

    fun rotation(rotation: Float): MarkerOptions = apply {
        google = google.rotation(rotation)
        huawei = huawei.rotation(rotation)
    }

    fun alpha(alpha: Float): MarkerOptions = apply {
        google = google.alpha(alpha)
        huawei = huawei.alpha(alpha)
    }
}
