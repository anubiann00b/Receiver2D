Receiver2D File Specifications
==============================

####.r2dw files
Mimetype: `application/vnd.r2dscene+xml`

```XML
<?xml version="1.0" encoding="ISO-8859-1"?>
<world>
	<scene name="Example Scene">
		<values>
			<int name="Default Enemy Strength">3000</int>
			<string name="Player Name">Mario</string>
			<int name="World Width">1000</int>
			<int name="World Height">1000</int>
		</values>
		<resources>
			<resource path="example-project/img/file.png" type="image/png" uuid="13408u34uhdas" />
			<resource path="example-project/misc/speech.txt" type="text/plain" uuid="8dh308asdhdh" />
		</resources>
		<entitylist>
			<entity name="Boss" x="30f" y="23f" rotation="190f">
				<component type="Rigidbody" mass="30.4f" velocity_x="2.3f" velocity_y="0.0f" />
				<component type="script" src="example/java/BossAI.java" />
			</entity>
			<entity name="Player" x="0f" y="0f" rotation="0f">
				<component type="CharacterController" speed="5.0f" jump_height="4.0f" />
			</entity>
		</entitylist>
	</scene>
</world>
```