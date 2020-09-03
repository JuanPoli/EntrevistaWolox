# EntrevistaWolox

The next is the resume of the automated tests runned for this application:

Wappi application resume
Bug (woloxInterviewPersonalInformationTest() – Line 97): 

Steps:
1)	Go to personal information section.
2)	Click upload image
3)	Fill all the inputs
4)	Save the information
5)	Go to Home Page
6)	Go to personal information again
7)	The image is not saved

Actual result:
The personal information upload image is not saved.

Expected result:
The uploaded image is saved and is displayed



Cambios a tener en cuenta:
- El botón de “Guardar” este desactivado si no hice ningún cambio. Y luego si, cuando hago un cambio que se active junto con el cancelar.
- Cuando cambio algún campo de información personal  y no lo guardo, sino que voy a otra sección, me pregunte si quiero guardar los cambios y no los descarte automáticamente.

