# Changelog

All notable changes to Coes Engine will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.0] - 2026-02-21

### Changed
- **Removed Fabric API dependency** - The mod now uses native Mixins for command registration, reducing footprint and dependency requirements.
- Updated documentation and README to reflect the removal of Fabric API.

## [1.0.0] - 2026-02-16

### Added
- Initial release of Coes Engine (formerly coes-custom-weapon)
- Data-driven RPG framework for custom weapons and abilities
- 9 built-in abilities:
  - Ignite - Sets targets on fire
  - Dash - Teleports player forward
  - Heal - Heals the player
  - Projectile - Launches projectiles
  - AoE - Area of effect damage
  - Teleport - Teleports to target location
  - Shield - Grants temporary damage immunity
  - Mana Cost - Requires mana to use abilities
  - Cooldown - Adds cooldown to abilities
- Complete mana management system
- Hot reload support via `/rpg reload` command
- Full command suite (`/rpg give`, `/rpg mana`, etc.)
- Automatic resource pack generation
- Cross-platform support (Java & Bedrock via Geyser)
- Automatic Geyser custom item mapping generation
- Cardinal Components API integration for player stats

### Changed
- Project renamed from "Coes Custom Weapon" to "Coes Engine"
- Mod ID changed from `coes-custom-weapon` to `coes-engine`
- Minecraft version support expanded to 1.21.1 - 1.21.11 (previously ~1.21.1)

### Technical Details
- Built on Fabric Loader 0.16.7+
- Requires Java 21+
- Uses Fabric API and Cardinal Components API
- Compatible with Minecraft 1.21.1 through 1.21.11
