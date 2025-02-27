package org.qo.plugin.qPluginTest

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class QPluginTest : JavaPlugin() {

	companion object {
		lateinit var instance: QPluginTest private set
	}

	fun getInstance(): QPluginTest = instance

	override fun onEnable() {
		instance = this
		Bukkit.getPluginManager().registerEvents(FriendlyTnt(), this)
		Bukkit.getPluginManager().registerEvents(BuffSnowball(), this)

		this.getCommand("firework")?.setExecutor(Firework())
		this.getCommand("newyeartnt")?.setExecutor(FriendlyTnt())
		this.getCommand("perfectSnowball")?.setExecutor(BuffSnowball())
	}

	override fun onDisable() {
	}
}