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

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.m0skit0.android.mapswrapper.CommonMap
import org.m0skit0.android.mapswrapper.MapView
import org.m0skit0.android.mapswrapper.OnMapReadyCallback
import org.m0skit0.android.mapswrapper.model.LatLng
import org.m0skit0.android.mapswrapper.model.MarkerOptions

/**
 * This shows how to create a simple activity with a raw MapView and add a marker to it. This
 * requires forwarding all the important lifecycle methods onto MapView.
 */
class RawMapViewDemoActivity : AppCompatActivity(), OnMapReadyCallback {
  private lateinit var mapView: MapView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.raw_mapview_demo)

    // *** IMPORTANT ***
    // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
    // objects or sub-Bundles.
    val mapViewBundle = savedInstanceState?.getBundle(MAPVIEW_BUNDLE_KEY)
    mapView = findViewById(R.id.map)
    mapView.onCreate(mapViewBundle)
    mapView.getMapAsync(this)
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    val mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY) ?: Bundle().apply {
      putBundle(MAPVIEW_BUNDLE_KEY, this)
    }
    mapView.onSaveInstanceState(mapViewBundle)
  }

  override fun onResume() {
    super.onResume()
    mapView.onResume()
  }

  override fun onStart() {
    super.onStart()
    mapView.onStart()
  }

  override fun onStop() {
    super.onStop()
    mapView.onStop()
  }

  override fun onMapReady(map: CommonMap) {
    map.addMarker(MarkerOptions().position(LatLng(0.0, 0.0)).title("Marker"))
  }

  override fun onPause() {
    mapView.onPause()
    super.onPause()
  }

  override fun onDestroy() {
    mapView.onDestroy()
    super.onDestroy()
  }

  override fun onLowMemory() {
    super.onLowMemory()
    mapView.onLowMemory()
  }

  companion object {
    private const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
  }
}
