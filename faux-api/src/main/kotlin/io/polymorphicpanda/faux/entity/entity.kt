package io.polymorphicpanda.faux.entity

import io.polymorphicpanda.faux.blueprint.Blueprint
import io.polymorphicpanda.faux.component.Component
import kotlin.reflect.KClass

typealias Entity = Int

abstract class EntityEditor {
    abstract val entity: Entity
    abstract fun <T: Component> get(componentType: KClass<T>): T
    abstract fun contains(componentType: KClass<out Component>): Boolean
    abstract fun <T: Component> add(componentType: KClass<T>, block: (T) -> Unit): EntityEditor
    abstract fun <T: Component> remove(componentType: KClass<T>): EntityEditor
    abstract fun destroy()

    inline fun <reified T: Component> add(noinline block: (T) -> Unit): EntityEditor = add(T::class, block)
    inline fun <reified T: Component> remove(): EntityEditor = remove(T::class)
    inline fun <reified T: Component> get() = get(T::class)
    inline fun <reified T: Component> contains() = contains(T::class)
}

interface Context {
    fun create(blueprint: Blueprint? = null): EntityEditor
    fun manage(entity: Entity): EntityEditor
}
