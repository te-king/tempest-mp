package world.components

import extensions.bindObjectTransformBuffer
import graphics.DrawCommandBuffer
import graphics.Material
import graphics.MeshBufferObject
import world.Node
import world.controllers.Renderer

class Drawable(node: Node) : Component(node), Renderer.Renderable {

    private val transform by component(::Transform)

    val pairs = mutableListOf<Pair<MeshBufferObject, Material?>>()


    override fun draw(commandBuffer: DrawCommandBuffer) {

        transform.worldMatrix
        commandBuffer.bindObjectTransformBuffer(transform.buffer)

        for ((material, pairs) in pairs.groupBy { it.second })
            material?.draw(commandBuffer, pairs.asSequence().map { it.first })

    }

}