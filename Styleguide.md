# Colors
- DARK_BLUE : #1E2730
- BLUE : #24303D
- LIGHT_BLUE: #3C4958
- SUCCESS: #1B7C12
- UGLY_GREEN (For Non-.eth adresses): #73A213
- SWITCH_GREEN (For Lightnode Switch): #94DE7E
- WARNING: #FFB526
- ERROR: #C63B3B
- GRAY: #9FA2A5
- WHITE: #FFFFFF
- DARK_BROWN: #3A2A0A


# Components

## Buttons
### Core Details for Buttons

Guideline for most buttons 

**Font Style**
font-size: 12.sp
line-height: 13.08.sp
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Icon**
Library: Icons.Rounded
Size: 24dp
Circle-size: 36dp  

**Padding** 
Horizontal: 8dp 
Vertical: 8dp

**Shape**
CircleShape

**Dimensions**
Height: 40.dp
Width: min. 127dp - „max-width“

**Extra**
Centered horizontally & vertically 
10.dp space between Icon and Text


### Basic Buttons
### Swipe button


<img width="538" alt="Bildschirm_foto 2023-09-19 um 16 01 36" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/2849f602-6322-405b-99d6-efc1be2594aa">

**Purpose**
sending/signing a transaction, or showing a private key or seed phrase

**Colors**
Background Color: #FFFFFF
Text Color: #24303D
Icon-background Color: #24303D
Icon Color: #FFFFFF

### Primary button


<img width="426" alt="Bildschirm_foto 2023-09-19 um 16 03 27" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/3f6c3350-b372-4ed0-829a-803d9a4d8991">

**Purpose**
execute primary functions, open System-UI dialogs

**Colors**
Background Color: #FFFFFF
Text Color: #24303D
Icon Color: #24303D

### Secondary button

<img width="418" alt="Bildschirm_foto 2023-09-19 um 16 06 41" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/8c93c303-77dd-43c9-9ebb-1e4f16dbbda7">

**Purpose**
execute secondary functions, open System-UI dialogs

**Colors**
Background Color: #24303D
Text Color: #FFFFFF
Icon Color: #FFFFFF

### Disabled button

<img width="419" alt="Bildschirm_foto 2023-09-19 um 16 05 14" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/522c3890-bc36-4365-8440-928aac2b39b6">

**Purpose**
displaying the inactivity of a button


**Colors**
Background Color: #9FA2A5
Text Color: #24303D
Icon Color: #24303D



### Advanced Buttons
### Circularbutton

<img width="140" alt="Bildschirm_foto 2023-09-19 um 16 07 23" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/766b79d5-f839-46b1-a8c6-cf0227bd8ca1">


**Purpose**
navigates to different to different parts of an app, usually in a group

**Colors**
Background Color: #FFFFFF
Text Color: #FFFFFF 
Icon Color: #24303D

**Font Style**
font-size: 14.sp
line-height: 14.08.sp
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Icon**
Size: 36dp
Circle-size: 64dp  

**Padding** 
Top: 4dp (For the Text - Component)

**Shape**
CircleShape

**Components used**
IconButton, Icon, Column, Text


### Tagbutton

<img width="185" alt="Bildschirm_foto 2023-09-19 um 16 09 06" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/1e21254e-a404-449f-a791-22fc1e0768ec">


**Purpose**
opening a list of options and displaying the selected option

**Colors**
Background Color: #24303D
Text Color: #FFFFFF 

**Font Style**
font-size: 16.sp
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Padding** 
contentPadding: 14dp (Horizontal), 0dp (Vertical)

**Shape**
CircleShape

**Components used**
Button, Row, Text

### Iconbutton

**Purpose**
enabling a simple action, described with an icon

**Colors**
Background Color: #24303D or Transparent
Icon Color: #FFFFFF 

**Icon**
Size: 32.dp

**Components used**
IconButton, Icon

## Dialogs

### System UI Dialog

(Image coming Soon)

**Purpose**
System UI

Background Color: #24303D
Title Color: #FFFFFF 
Heading Color: #FFFFFF
Text Color: #FFFFFF 

### Info Dialog

<img width="328" alt="Bildschirm_foto 2023-09-19 um 16 12 56" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/9c7a77d0-4642-4101-a9c1-fd02827eac81">

**Purpose**
inform or warn user about aspects about the app

**Colors**
Background Color: #24303D
Title Color: #FFFFFF 
Heading Color: #FFFFFF
Text Color: #9FA2A5

**Title - Font Style**
font-size: 24.sp
font-family: Inter
text-Align: TextAlign.Center
font-weight: FontWeight(600) / Semi-Bold

**Text - Font Style**
font-size: 16.sp
font-family: Inter
text-Align: TextAlign.Center
font-weight: FontWeight.Normal

**Heading - Font Style**
*(used to display specific details)*
font-size: 16.sp
font-family: Inter
text-Align: TextAlign.Left
font-weight: FontWeight.SemiBold

**Text under Heading - Font Style**
text-Align: TextAlign.Left
Rest is the same to "Text-Font Style"

**Icon**
Size: 30dp

**Padding** 
All sides: 28dp 

**Shape**
RoundedCornerShape:  16dp

**Components used**
Dialog, Surface, Box, Column, Icon, Text, Spacer *(optional)*

**Details**

## Textfields
### Core details for Textfields

**Colors**
Background Color: #24303D
Placeholder Color: #9FA2A5
Text Color: #FFFFFF 

**Shape**
RoundedCornerShape:  10%

**Icons**
Library: Icons.Rounded
Size: 32dp

**Padding**
horizontal: 16dp
vertical: 8dp

**Placeholder - Font Style**
font-size: 18.sp
font-family: Inter
font-weight: FontWeight.Normal

**Text - Font Style**
font-size: 18.sp
font-family: Inter
font-weight: FontWeight.Normal

**Components used**
BasicTextField, Row, Box, Spacer


### Textfield w/ trailing icon with action

<img width="430" alt="Bildschirm_foto 2023-09-19 um 16 25 53" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/88074f7a-066b-414b-b646-57ecd30cd6b0">

**Dimension**
height: 64.dp

**Additional**
(optional) Iconbutton or (optional) Tagbutton

### Textfield w/ leading icon without action

<img width="409" alt="Bildschirm_foto 2023-09-19 um 17 44 47" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/5629d5b9-73f4-417b-b020-44a8b5a51d33">

**Dimension**
height: 64.dp

**Additional**
Icon

### TextArea

**Dimension**
min-height: 100.dp

## ImageBox

<img width="336" alt="Bildschirm_foto 2023-09-19 um 16 29 20" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/1c3d6b21-bba4-461e-97aa-e14af967e9de">

**Colors**
Background Color: #24303D
Text Color: #FFFFFF 

**Shape**
RoundedCornerShape:  24dp

**Text - Font Style**
font-size: 18.sp
font-family: Inter
font-weight: FontWeight.SemiBold

**Icons**
Library: Icons.Outlined
Size: 28dp

## Cards

<img width="474" alt="Bildschirm_foto 2023-09-19 um 17 50 03" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/3d437302-7cc4-44db-b29c-759571e8ebf0">

**Purpose**
display information

**Colors**
Background Color: #24303D
Title Color: #FFFFFF 
Text Color: #FFFFFF 
Subtext Color: #9FA2A5

**Title - Font Style**
font-size: 18.sp
font-family: Inter
text-Align: TextAlign.Center
font-weight: FontWeight(600) / Semi-Bold

**Text - Font Style**
font-size: 16.sp
font-family: Inter
text-Align: TextAlign.Center
font-weight: FontWeight.Normal

**Subtext Style**
font-size: 14.sp
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Padding** 
All sides: 16dp

**Shape**
RoundedCornerShape: 16dp

## Dropdowns

### Dropdown (Normal)

<img width="481" alt="Bildschirm_foto 2023-09-19 um 16 36 53" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/75a18362-8fa5-4e42-bf47-d9ce17758ae8">

**Purpose**
select between options

**Colors**
Background Color: #24303D
Text Color: #FFFFFF 
Icon Color: #FFFFFF 
Dropdown Color: #3C4958 
Selected Background Color: #24303D

**Icon**
Library: Icons.Rounded.Dropdown
Size: 24dp 

**Title - Font Style**
font-size: 18.sp
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Dimension**
height: 64.dp

**Components used**
Surface, Row, Box, Column, DropdownMenu, DropdownMenuItem


## Switch

<img width="298" alt="Bildschirm_foto 2023-09-19 um 16 36 19" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/6b60dabd-cc60-41f7-b8db-2a95e5284eaa">

**Purpose**
allows users to turn sates ON or OFF

**Colors**
Background "OFF" Color: #9FA2A5 
Background "ON" Color: #94DE7E
Text Color: #FFFFFF 

**Thumb-Style**
Library: Icons.Rounded.Dropdown
Size: 24dp 

**Text - Font Style**
font-size: 18.sp
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Shape**
Shape: RoundedCornerShape(50.dp)

**Padding**
All sides: 4.dp

**Components used**
BoxWithConstraints, Row, Box, Text, Spacer

## Listitem

### ListItem (Normal)

<img width="442" alt="Bildschirm_foto 2023-09-19 um 16 42 03" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/3551d780-0170-44e1-8368-2090354dc8a5">


**Purpose**
displays a row of information

**Colors**
Background Color: #24303D
Title Color: #FFFFFF 
Subtext Color: #9FA2A5  
Trailing Title Color: #FFFFFF 
Trailing Subtext Color: #9FA2A5  
Icon Color: Variable
Background: #FFFFFF 

**Icon - Style**
Library: Icons.Rounded
Size: 24dp
Icons: Icons.Filled.ExpandLess & Icons.Filled.ExpandMore

**Icon Background - Style**
Shape: RoundedCornerShape: 12.dp
Size: 42dp

**Title - Font Style**
font-size: 18.sp
font-family: Inter
font-weight: Normal

**Subtext - Font Style**
font-size: 32.sp
font-family: Inter
font-weight: Normal

**Trailing Title - Font Style**
font-size: 20.sp
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Trailing Subtext - Font Style**
font-size: 32.sp
font-family: Inter
font-weight: Normal

**Shape**
CornerRadius: RoundedCornerShape(12.dp)

**Components used**
Card(optional), Row, Column, Icon, Box

### ExpandableListItem

<img width="449" alt="Bildschirm_foto 2023-09-19 um 16 47 34" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/40e42a8c-7575-49fa-babc-0418acfa65aa">

<img width="387" alt="Bildschirm_foto 2023-09-19 um 16 48 02" src="https://github.com/EthereumPhone/ethOS-Component-Library/assets/31884918/447ff11a-66de-4221-8674-1a1fc5a61842">

**Purpose**
displays a row of information, mostly assets, with expandable information associated with the item

**Colors**
Background Color: #24303D
Title Color: #FFFFFF 
Text Color: #FFFFFF 
Icon Color: #FFFFFF 
Selected Background Color: #24303D 

**Expand Icon - Style**
Library: Icons.Filled
Size: 24dp
Icons: Icons.Filled.ExpandLess & Icons.Filled.ExpandMore

**Supporting Icon - Style**
Size: 20dp

**Title - Font Style**
font-size: 20.sp
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Text - Font Style**
font-size: 32.sp
font-family: Inter
font-weight: Normal

**Shape**
CornerRadius: RoundedCornerShape(12.dp)

**Components used**
AnimatedVisibility, Iconbutton, Column, ListItem, Text

## Tab
(Image coming soon)

**Purpose**
enable quick access to content

**Colors**
Background Color: #24303D
Background Text Color: #FFFFFF 
Selected Background Color: #FFFFFF 
Selected Background Text Color: #24303D 

**Shape**
Corner-radius: RoundedCornerShape(50%)

**Text - Style**
Uppercase text
