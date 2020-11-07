// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.demos.maps.demos

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemos.OnMapAndViewReadyListener.OnGlobalLayoutAndMapReadyListener
import org.m0skit0.android.mapswrapper.CameraUpdateFactory
import org.m0skit0.android.mapswrapper.CommonMap
import org.m0skit0.android.mapswrapper.SupportMapFragment
import org.m0skit0.android.mapswrapper.model.*

/**
 * This demo shows some features supported in lite mode.
 * In particular it demonstrates the use of [org.m0skit0.android.mapswrapper.model.Marker]s to
 * launch the Google Maps Mobile application, [org.m0skit0.android.mapswrapper.CameraUpdate]s
 * and [org.m0skit0.android.mapswrapper.model.Polygon]s.
 */
class LiteDemoActivity : AppCompatActivity(), OnGlobalLayoutAndMapReadyListener {
    private lateinit var map: CommonMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout
        setContentView(R.layout.lite_demo)

        // Get the map and register for the ready callback
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        OnMapAndViewReadyListener(mapFragment, this)
    }

    /**
     * Move the camera to center on Darwin.
     */
    fun showDarwin(v: View?) {
        if (!::map.isInitialized) return

        // Center camera on Adelaide marker
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(DARWIN, 10f))
    }

    /**
     * Move the camera to center on Adelaide.
     */
    fun showAdelaide(v: View?) {
        if (!::map.isInitialized) return

        // Center camera on Adelaide marker
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ADELAIDE, 10f))
    }

    /**
     * Move the camera to show all of Australia.
     * Construct a [org.m0skit0.android.mapswrapper.model.LatLngBounds] from markers positions,
     * then move the camera.
     */
    fun showAustralia(v: View?) {
        if (!::map.isInitialized) return

        // Create bounds that include all locations of the map
        val boundsBuilder = LatLngBounds.builder()
            .include(PERTH)
            .include(ADELAIDE)
            .include(MELBOURNE)
            .include(SYDNEY)
            .include(DARWIN)
            .include(BRISBANE)

        // Move camera to show all markers and locations
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 20))
    }

    /**
     * Called when the map is ready to add all markers and objects to the map.
     */
    override fun onMapReady(googleMap: CommonMap?) {
        // return early if the map was not initialised properly
        map = googleMap ?: return
        addMarkers()
        addPolyObjects()
        showAustralia(null)
    }

    /**
     * Add a Polyline and a Polygon to the map.
     * The Polyline connects Melbourne, Adelaide and Perth. The Polygon is located in the Northern
     * Territory (Australia).
     */
    private fun addPolyObjects() {
        map.addPolyline(
            PolylineOptions()
                .add(
                    MELBOURNE,
                    ADELAIDE,
                    PERTH
                )
                .color(Color.GREEN)
                .width(5f)
        )
        map.addPolygon(
            PolygonOptions()
                .add(*POLYGON)
                .fillColor(Color.CYAN)
                .strokeColor(Color.BLUE)
                .strokeWidth(5f)
        )
    }

    /**
     * Add Markers with default info windows to the map.
     */
    private fun addMarkers() {
        map.addMarker(
            MarkerOptions()
                .position(BRISBANE)
                .title("Brisbane")
        )
        map.addMarker(
            MarkerOptions()
                .position(MELBOURNE)
                .title("Melbourne")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        )
        map.addMarker(
            MarkerOptions()
                .position(SYDNEY)
                .title("Sydney")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        )
        map.addMarker(
            MarkerOptions()
                .position(ADELAIDE)
                .title("Adelaide")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        )
        map.addMarker(
            MarkerOptions()
                .position(PERTH)
                .title("Perth")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
        )
    }

    companion object {
        private val BRISBANE = LatLng(-27.47093, 153.0235)
        private val MELBOURNE = LatLng(-37.81319, 144.96298)
        private val SYDNEY = LatLng(-33.87365, 151.20689)
        private val ADELAIDE = LatLng(-34.92873, 138.59995)
        private val PERTH = LatLng(-31.952854, 115.857342)
        private val DARWIN = LatLng(-12.459501, 130.839915)

        /**
         * A Polygon with five points in the Norther Territory, Australia.
         */
        private val POLYGON = arrayOf(
            LatLng(-18.000328, 130.473633), LatLng(-16.173880, 135.087891),
            LatLng(-19.663970, 137.724609), LatLng(-23.202307, 135.395508),
            LatLng(-19.705347, 129.550781)
        )
    }
}
