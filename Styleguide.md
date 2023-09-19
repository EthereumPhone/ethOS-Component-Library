# Components

## Buttons
### Core Details for Buttons

Guideline for most buttons 

**Font Style**
font-size: 12.sp
line-height: 13.08.sp
font-family: Inter
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
10.dp space between Icons


### Basic Buttons
#### Swipe button

**Purpose**
sending/signing a transaction, or showing a private key or seed phrase

**Colors**
Background Color: #FFFFFF
Text Color: #24303D
Icon-background Color: #24303D
Icon Color: #FFFFFF

#### Primary button

**Purpose**
execute primary functions, open System-UI dialogs

**Colors**
Background Color: #FFFFFF
Text Color: #24303D
Icon Color: #24303D

#### Secondary button

**Purpose**
execute secondary functions, open System-UI dialogs

**Colors**
Background Color: #24303D
Text Color: #FFFFFF
Icon Color: #FFFFFF

#### Disabled button

**Purpose**
displaying the inactivity of a button

**Colors**
Background Color: #9FA2A5
Text Color: #24303D
Icon Color: #24303D



### Advanced Buttons
#### Circularbutton

**Purpose**
navigates to different to different parts of an app, usually in a group

**Colors**
Background Color: #FFFFFF
Text Color: #FFFFFF 
Icon Color: #24303D

**Font Style**
font-size: 14.sp
line-height: 14.08.sp
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Icon**
Size: 36dp
Circle-size: 64dp  

**Padding** 
Top: 4dp (For the Text - Component)

**Shape**
CircleShape

**Components used**
IconButton, Icon, Column, Text


#### Tagbutton

**Purpose**
opening a list of options and displaying the selected option

**Colors**
Background Color: #24303D
Text Color: #FFFFFF 

**Font Style**
font-size: 16.sp
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Padding** 
contentPadding: 14dp (Horizontal), 0dp (Vertical)

**Shape**
CircleShape

**Components used**
Button, Row, Text

#### Iconbutton

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

#### System UI Dialog

**Purpose**
System UI

Background Color: #24303D
Title Color: #FFFFFF 
Heading Color: #FFFFFF
Text Color: #FFFFFF 

#### Info Dialog

**Purpose**
inform or warn user about aspects about the app

**Colors**
Background Color: #24303D
Title Color: #FFFFFF 
Heading Color: #FFFFFF
Text Color: #9FA2A5

**Title - Font Style**
font-size: 24.sp
font-family: Inter
text-Align: TextAlign.Center
font-weight: FontWeight(600) / Semi-Bold

**Text - Font Style**
font-size: 16.sp
font-family: Inter
text-Align: TextAlign.Center
font-weight: FontWeight.Normal

**Heading - Font Style**
*(used to display specific details)*
font-size: 16.sp
font-family: Inter
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


## Textfields
#### Core details for Textfields

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
font-family: Inter
font-weight: FontWeight.Normal

**Text - Font Style**
font-size: 18.sp
font-family: Inter
font-weight: FontWeight.Normal

**Components used**
BasicTextField, Row, Box, Spacer


#### Textfield w/ trailing icon with action

**Dimension**
height: 64.dp

**Additional**
(optional) Iconbutton or (optional) Tagbutton

#### Textfield w/ leading icon without action

**Dimension**
height: 64.dp

**Additional**
Icon

#### TextArea

**Dimension**
min-height: 100.dp






## Cards

**Purpose**
display information

**Colors**
Background Color: #24303D
Title Color: #FFFFFF 
Text Color: #FFFFFF 
Subtext Color: #9FA2A5

**Title - Font Style**
font-size: 18.sp
font-family: Inter
text-Align: TextAlign.Center
font-weight: FontWeight(600) / Semi-Bold

**Text - Font Style**
font-size: 16.sp
font-family: Inter
text-Align: TextAlign.Center
font-weight: FontWeight.Normal

**Subtext Style**
font-size: 14.sp
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Padding** 
All sides: 16dp

**Shape**
RoundedCornerShape: 16dp

## Dropdowns

#### Dropdown (Normal)

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
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Dimension**
height: 64.dp

**Components used**
Surface, Row, Box, Column, DropdownMenu, DropdownMenuItem


## Switch

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
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Shape**
Shape: RoundedCornerShape(50.dp)

**Padding**
All sides: 4.dp

**Components used**
BoxWithConstraints, Row, Box, Text, Spacer

## Listitem

#### ListItem (Normal)

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
font-family: Inter
font-weight: Normal

**Subtext - Font Style**
font-size: 32.sp
font-family: Inter
font-weight: Normal

**Trailing Title - Font Style**
font-size: 20.sp
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Trailing Subtext - Font Style**
font-size: 32.sp
font-family: Inter
font-weight: Normal

**Shape**
CornerRadius: RoundedCornerShape(12.dp)

**Components used**
Card(optional), Row, Column, Icon, Box

#### ExpandableListItem

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
font-family: Inter
font-weight: FontWeight(600) / Semi-Bold

**Text - Font Style**
font-size: 32.sp
font-family: Inter
font-weight: Normal

**Shape**
CornerRadius: RoundedCornerShape(12.dp)

**Components used**
AnimatedVisibility, Iconbutton, Column, ListItem, Text



## Tab

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