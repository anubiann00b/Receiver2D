Receiver2D File Specifications
==============================

####.r2ds files
```XML
<?xml version="1.0" encoding="ISO-8859-1"?>
<world>
	<scene name="Example Scene">
		<values>
			<int name="Default Enemy Strength">3000</int>
			<string name="Player Name">Mario</string>
			<double name="World Size">345.23</double>
		</values>
		<resources>
			<resource path="example-project/img/file.png" type="image/png" uuid="13408u34uhdas" />
			<resource path="example-project/misc/speech.txt" type="text/plain" uuid="8dh308asdhdh" />
		</resources>
		<entitylist>
			<entity name="Boss" x=30 y=23 rotation=190>
				<component type="Rigidbody" mass=30.4 velocity_x=2.3 velocity_y=0.0 />
				<component type="script" src="example/java/BossAI.java" />
			</entity>
			<entity name="Player" x=0 y=0 rotation=0>
				<component type="CharacterController" speed=5.0 jump_height=4.0 />
			</entity>
		</entitylist>
	</scene>
</world>
```
