package org.qo.plugin.qPluginTest

import org.bukkit.plugin.java.JavaPlugin

class QPluginTest : JavaPlugin() {

	companion object {
		lateinit var instance: QPluginTest private set
	}

	fun getInstance(): QPluginTest = instance

	override fun onEnable() {
		instance = this
		this.getCommand("firework")?.setExecutor(Firework())
	}

	override fun onDisable() {
	}
}