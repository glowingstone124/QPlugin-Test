package org.qo.plugin.qPluginTest

import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Firework
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.FireworkMeta
import kotlin.properties.Delegates
import kotlin.random.Random

class Firework : CommandExecutor {
	override fun onCommand(
		p0: CommandSender,
		p1: Command,
		p2: String,
		p3: Array<out String>?
	): Boolean {
		if (p0 !is Player) {
			p0.sendMessage("只有玩家可以使用该指令！")
			return false
		}
		if (p3!!.size != 1 && p3.size != 2) {
			p0.sendMessage("请指定操作为 get or launch")
			return true
		}
		if (p3[0] == "get") {
			var opt by Delegates.notNull<Int>()
			try {
				opt = p3[1].toInt()
			} catch (e: NumberFormatException) {
				p0.sendMessage("必须是数字")
				return false;
			}
			val firework = ItemStack(Material.FIREWORK_ROCKET).add(16)
			val meta = firework.itemMeta as FireworkMeta

			val effects = getDesiredFireworkEffect(opt)
			if (effects.isEmpty()) {
				p0.sendMessage("您选择的烟花类型不存在")
				return true
			}
			meta.apply {
				power = 4
				effects.forEach { addEffect(it) }
			}

			firework.itemMeta = meta
			p0.inventory.addItem(firework)
			p0.sendMessage("Complete.")
			return true
		} else if (p3[0] == "launch") {
			handleLaunchFireworksCommand(p0)
			return true
		}
		return false
	}

	private fun handleLaunchFireworksCommand(player:Player){
		val loc  = player.location
		val world = player.world
		repeat (10) {
			val offsetX = Random.nextDouble(-5.0, 5.0)
			val offsetZ = Random.nextDouble(-5.0, 5.0)
			val fireworkLocation = loc.clone().add(offsetX, 0.0, offsetZ)
			val firework = world.spawn(fireworkLocation, Firework::class.java)
			val effects = getDesiredFireworkEffect(5)
			val fireworkMeta = firework.fireworkMeta
			fireworkMeta.apply {
				power = 4
				effects.forEach { effect ->
					fireworkMeta.addEffect(effect)
				}
			}
			firework.fireworkMeta = fireworkMeta
		}
	}


	fun getDesiredFireworkEffect(opt: Int): List<FireworkEffect> {
		return when (opt) {
			1 -> listOf(
				FireworkEffect.builder()
					.with(FireworkEffect.Type.STAR)
					.withColor(Color.RED, Color.ORANGE)
					.withFade(Color.YELLOW)
					.trail(true)
					.build(),
				FireworkEffect.builder()
					.with(FireworkEffect.Type.BALL_LARGE)
					.withColor(Color.LIME, Color.GREEN)
					.trail(true)
					.build(),
				FireworkEffect.builder()
					.with(FireworkEffect.Type.BALL)
					.withColor(Color.ORANGE, Color.YELLOW)
					.trail(true)
					.flicker(true)
					.build()
			)

			2 -> listOf(
				FireworkEffect.builder()
					.with(FireworkEffect.Type.CREEPER)
					.withColor(Color.GREEN, Color.OLIVE)
					.withFade(Color.FUCHSIA)
					.trail(true)
					.build(),
				FireworkEffect.builder()
					.with(FireworkEffect.Type.BALL)
					.withColor(Color.GREEN)
					.trail(true)
					.flicker(true)
					.build()
			)

			3 -> listOf(
				FireworkEffect.builder()
					.with(FireworkEffect.Type.BURST)
					.withColor(Color.RED, Color.YELLOW)
					.trail(true)
					.flicker(true)
					.build(),
				FireworkEffect.builder()
					.with(FireworkEffect.Type.BALL_LARGE)
					.withColor(Color.ORANGE)
					.trail(true)
					.flicker(true)
					.build()
			)

			4 -> listOf(
				FireworkEffect.builder()
					.with(FireworkEffect.Type.BURST)
					.withColor(Color.RED, Color.YELLOW)
					.trail(true)
					.flicker(true)
					.build(),
				FireworkEffect.builder()
					.with(FireworkEffect.Type.BURST)
					.withColor(Color.RED, Color.YELLOW)
					.trail(true)
					.flicker(true)
					.build(),
				FireworkEffect.builder()
					.with(FireworkEffect.Type.BURST)
					.withColor(Color.RED, Color.YELLOW)
					.trail(true)
					.flicker(true)
					.build(),
				FireworkEffect.builder()
					.with(FireworkEffect.Type.BURST)
					.withColor(Color.RED, Color.YELLOW)
					.trail(true)
					.flicker(true)
					.build(),
				FireworkEffect.builder()
					.with(FireworkEffect.Type.BURST)
					.withColor(Color.RED, Color.YELLOW)
					.trail(true)
					.flicker(true)
					.build(),
				FireworkEffect.builder()
					.with(FireworkEffect.Type.BURST)
					.withColor(Color.RED, Color.YELLOW)
					.trail(true)
					.flicker(true)
					.build(),
				FireworkEffect.builder()
					.with(FireworkEffect.Type.BURST)
					.withColor(Color.RED, Color.YELLOW)
					.trail(true)
					.flicker(true)
					.build(),
				FireworkEffect.builder()
					.with(FireworkEffect.Type.BURST)
					.withColor(Color.RED, Color.YELLOW)
					.trail(true)
					.flicker(true)
					.build()
			)

			5 -> listOf(
				FireworkEffect.builder()
					.with(FireworkEffect.Type.STAR)
					.withColor(Color.ORANGE)
					.trail(true)
					.flicker(false)
					.build()
			)
			else -> emptyList()
		}
	}
}
