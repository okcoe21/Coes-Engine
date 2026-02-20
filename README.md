# Coes Engine

A powerful, data-driven RPG framework for Minecraft (Fabric) that enables easy creation of custom weapons, abilities, and spells without code modifications. Features full cross-platform support for both Java and Bedrock editions via Geyser integration.

## Features

- **Data-Driven Design** - Create custom weapons and abilities using simple JSON configuration files
- **Hot Reload** - Automatically reload configurations with `/rpg reload` command
- **Cross-Platform** - Full Java and Bedrock Edition support via automatic Geyser mapping generation
- **Rich Ability System** - 9 built-in abilities: Ignite, Dash, Heal, Projectile, AoE, Teleport, Shield, Mana Cost, Cooldown
- **Mana System** - Complete mana management with commands and components
- **Automatic Resource Pack Generation** - Auto-generates resource packs for custom items

## Requirements

- **Minecraft**: 1.21.1 - 1.21.11
- **Fabric Loader**: 0.16.7 or higher
- **Java**: 21 or higher
- **Dependencies**:
  - [Cardinal Components API](https://www.curseforge.com/minecraft/mc-mods/cardinal-components-api) (for mana/stats)

## Installation

1. Download the latest release from the [Releases](../../releases) page
2. Place `coes-engine-*.jar` in your `mods` folder
3. Install required dependencies (Cardinal Components API)
4. Start your server/client
5. Find auto-generated resource packs in `generated/` folder

## Quick Start

### Creating Your First Weapon

1. Create a weapon configuration file in `config/rpg/weapons/my_weapon.json`:

```json
{
  "id": "fire_sword",
  "type": "sword",
  "texture": "fire_sword.png",
  "abilities": {
    "right_click": [
      "mana_cost:10",
      "projectile:fireball",
      "cooldown:2"
    ],
    "on_hit": [
      "ignite:3"
    ]
  }
}
```

2. Place your texture in `config/rpg/textures/fire_sword.png`
3. Run `/rpg reload` in-game
4. Get your weapon with `/rpg give <player> fire_sword`

## Available Abilities

| Ability | Syntax | Description | Example |
|---------|--------|-------------|---------|
| **Ignite** | `ignite:<duration>` | Sets target on fire | `ignite:5` |
| **Dash** | `dash:<distance>` | Teleports player forward | `dash:10` |
| **Heal** | `heal:<amount>` | Heals the player | `heal:4` |
| **Projectile** | `projectile:<type>` | Launches a projectile | `projectile:fireball` |
| **AoE** | `aoe:<radius>:<damage>` | Area of effect damage | `aoe:5:6` |
| **Teleport** | `teleport:<distance>` | Teleports to target location | `teleport:20` |
| **Shield** | `shield:<duration>` | Grants temporary damage immunity | `shield:10` |
| **Mana Cost** | `mana_cost:<amount>` | Requires mana to use | `mana_cost:10` |
| **Cooldown** | `cooldown:<seconds>` | Adds cooldown to ability | `cooldown:3` |

### Ability Triggers

Abilities can be triggered on different events:

- `right_click` - When right-clicking with the weapon
- `on_hit` - When hitting an entity
- `passive` - Continuous effect while holding the weapon

## Commands

| Command | Permission | Description |
|---------|------------|-------------|
| `/rpg reload` | `rpg.reload` | Reload all weapon configurations |
| `/rpg give <player> <weapon>` | `rpg.give` | Give a custom weapon to a player |
| `/rpg mana get [player]` | `rpg.mana` | Check mana of player |
| `/rpg mana set <player> <amount>` | `rpg.mana` | Set player's mana |
| `/rpg mana add <player> <amount>` | `rpg.mana` | Add mana to player |
| `/rpg mana remove <player> <amount>` | `rpg.mana` | Remove mana from player |

## Configuration

### Weapon Configuration

Create JSON files in `config/rpg/weapons/`:

```json
{
  "id": "unique_weapon_id",
  "type": "sword|stick|bow|trident",
  "texture": "texture_filename.png",
  "abilities": {
    "right_click": ["ability1", "ability2"],
    "on_hit": ["ability3"],
    "passive": ["ability4"]
  }
}
```

### Texture Files

Place your custom textures (16x16 PNG) in `config/rpg/textures/`

### Resource Pack

The mod automatically generates a resource pack in `generated/rpg_pack/`. For clients:
1. Copy the generated pack to your resource packs folder
2. Enable it in Minecraft settings

## Geyser/Bedrock Support

The mod automatically generates Geyser custom item mappings in `generated/geyser_mappings.json`. 

To use:
1. Copy the generated file to your Geyser `custom_mappings` folder
2. Restart Geyser
3. Bedrock players will see custom textures and can use all abilities

## Building from Source

```bash
git clone https://github.com/okcoe21/Coes-Engine.git
cd Coes-Engine
./gradlew build
```

The compiled JAR will be in `build/libs/coes-engine-*.jar`

## Example Weapons

### Staff of Fire
```json
{
  "id": "fire_staff",
  "type": "stick",
  "texture": "fire_staff.png",
  "abilities": {
    "right_click": [
      "mana_cost:10",
      "projectile:fireball",
      "cooldown:2"
    ],
    "on_hit": [
      "ignite:3"
    ]
  }
}
```

### Healing Wand
```json
{
  "id": "healing_wand",
  "type": "stick",
  "texture": "healing_wand.png",
  "abilities": {
    "right_click": [
      "mana_cost:15",
      "heal:10",
      "cooldown:5"
    ]
  }
}
```

### Teleport Blade
```json
{
  "id": "teleport_blade",
  "type": "sword",
  "texture": "teleport_blade.png",
  "abilities": {
    "right_click": [
      "mana_cost:20",
      "teleport:15",
      "cooldown:3"
    ],
    "on_hit": [
      "dash:3"
    ]
  }
}
```

## Troubleshooting

### Weapon not appearing?
- Check `/rpg reload` output for errors
- Verify JSON syntax is valid
- Ensure texture file exists in `config/rpg/textures/`

### Abilities not working?
- Check player has sufficient mana (`/rpg mana get`)
- Verify cooldown has expired
- Check console for error messages

### Bedrock textures not showing?
- Ensure Geyser mappings are copied to the correct folder
- Restart Geyser server after adding mappings
- Verify texture dimensions are 16x16 pixels

## Credits

- Built with [Fabric](https://fabricmc.net/)
- Uses [Cardinal Components API](https://github.com/Ladysnake/Cardinal-Components-API) for data storage
- Developed by [okcoe21](https://github.com/okcoe21)

## Contributing

Contributions are welcome! Feel free to:
- Report bugs via [Issues](../../issues)
- Submit feature requests
- Create pull requests

---

**Note**: This mod is under active development. Features and APIs may change between versions.
