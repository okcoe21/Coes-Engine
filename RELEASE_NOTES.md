# Coes Engine v1.1.0 - Zero Dependency Update

## Introducing Coes Engine

A powerful, data-driven RPG framework for Minecraft (Fabric) that revolutionizes how you create custom weapons, abilities, and spells. Build entire RPG systems using simple JSON configuration files - **no code modifications required**.

## What's Included

### Core Features
- **Data-Driven Design** - Create custom weapons with JSON files in minutes
- **Hot Reload System** - Instant configuration reloading with `/rpg reload`
- **Cross-Platform Ready** - Full Java & Bedrock Edition support via Geyser
- **9 Built-in Abilities** - Complete toolkit for creating powerful weapons
- **Mana Management** - Comprehensive mana system with commands
- **Auto Resource Packs** - Automatic generation for custom textures

### Ability System
Choose from 9 powerful abilities to customize your weapons:
- **Ignite** - Set enemies ablaze
- **Dash** - Quick movement bursts
- **Heal** - Self-healing capabilities
- **Projectile** - Launch magical projectiles
- **AoE** - Area damage effects
- **Teleport** - Instant repositioning
- **Shield** - Temporary invulnerability
- **Mana Cost** - Balance with resource management
- **Cooldown** - Prevent ability spam

### Commands
Full command suite for server management:
- `/rpg reload` - Reload all configurations
- `/rpg give <player> <weapon>` - Give custom weapons
- `/rpg mana get/set/add/remove` - Complete mana control

### Developer Features
- Automatic resource pack generation
- Geyser custom item mapping generation
- Hot-reloadable configurations
- Extensible ability system
- Cardinal Components integration

## Requirements
- **Minecraft**: 1.21.1 - 1.21.11
- **Fabric Loader**: 0.16.7+
- **Java**: 21+
- **Dependencies**: Cardinal Components API (Fabric API is no longer required!)

## Quick Start

1. Install the mod and dependencies
2. Create a weapon config in `config/rpg/weapons/my_weapon.json`
3. Add your texture to `config/rpg/textures/`
4. Run `/rpg reload`
5. Get your weapon with `/rpg give <player> <weapon_id>`

See the [README](README.md) for detailed documentation and examples.

## Bedrock/Geyser Support

Fully compatible with Geyser! The mod automatically generates custom item mappings for seamless Bedrock Edition integration. Copy the generated `geyser_mappings.json` to your Geyser server and restart.

## What's Changed
- **Removed Fabric API dependency** - The mod now uses native Mixins for command registration
- Project renamed to "Coes Engine" for broader scope
- Extended Minecraft version support (1.21.1 - 1.21.11)
- Comprehensive documentation added

## Documentation
Check out the [README](README.md) for:
- Complete ability reference
- Configuration examples
- Three example weapons
- Troubleshooting guide
- Building from source

## Credits
Built with [Fabric](https://fabricmc.net/) and [Cardinal Components API](https://github.com/Ladysnake/Cardinal-Components-API)

---

**Full Changelog**: See [CHANGELOG.md](CHANGELOG.md)
