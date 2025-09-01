package org.ethosmobile.components.library.opengl.shapes


class Globe {
    // Number of latitudes and longitudes to generate the wireframe
    private val latitudeBands = 20
    private val longitudeBands = 20

    // Arrays to store the vertices and indices
    val vertices: FloatArray
    val indices: ShortArray

    init {
        // Temporary lists to hold generated vertices and indices
        val verticesList = mutableListOf<Float>()
        val indicesList = mutableListOf<Short>()

        // Generate the vertices for the sphere (globe)
        for (lat in 0..latitudeBands) {
            val theta = Math.PI * lat / latitudeBands
            val sinTheta = Math.sin(theta)
            val cosTheta = Math.cos(theta)

            for (lon in 0..longitudeBands) {
                val phi = 2 * Math.PI * lon / longitudeBands
                val sinPhi = Math.sin(phi)
                val cosPhi = Math.cos(phi)

                val x = (cosPhi * sinTheta).toFloat()
                val y = cosTheta.toFloat()
                val z = (sinPhi * sinTheta).toFloat()

                // Add each vertex's position (x, y, z)
                verticesList.add(x)
                verticesList.add(y)
                verticesList.add(z)
            }
        }

        // Generate the indices for the wireframe lines
        for (lat in 0 until latitudeBands) {
            for (lon in 0 until longitudeBands) {
                val first = (lat * (longitudeBands + 1) + lon).toShort()
                val second = (first + longitudeBands + 1).toShort()

                // Latitude lines (horizontal lines)
                indicesList.add(first)
                indicesList.add((first + 1).toShort())

                // Longitude lines (vertical lines)
                indicesList.add(first)
                indicesList.add(second)
            }
        }

        // Convert lists to arrays for OpenGL
        vertices = verticesList.toFloatArray()
        indices = indicesList.toShortArray()
    }
}

