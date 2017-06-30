# MMTF JavaScript API and NGL Viewer/Library

Introduction to NGL Viewer/Library and the MMTF JavaScript API. View slides online [here](1-ngl-viewer/MMTF2017-JavaScript-API-NGL-Viewer-Library.pdf)

* Gitter chat room https://gitter.im/nglviewer/Lobby


## Resources

* MMTF JavaScript API
  * Github repository https://github.com/rcsb/mmtf-javascript
  * API docs https://rcsb.github.io/mmtf-javascript/docs/api/v1.0.0/
  * Examples
    * JSON https://codepen.io/arose/pen/LLzVXg
    * Traversal https://codepen.io/arose/pen/QgqbRG
    * Loader https://codepen.io/arose/pen/qjPdzQ
  * CDN https://cdn.rawgit.com/rcsb/mmtf-javascript/v1.0.0/dist/mmtf.js


* NGL Viewer/Library
  * Github repository https://github.com/arose/ngl
  * API docs & manual http://arose.github.io/ngl/api/
  * Examples Gallery https://arose.github.io/ngl/gallery/
  * CodePen template https://codepen.io/pen?template=JNLMXb
  * CDN
    * https://unpkg.com/ngl (latest release)
    * https://unpkg.com/ngl@0.10.3


## Exercise ideas

* MMTF JavaScript API
  * Play around with examples on CodePen
  * Change examples to use MMTF.fetch()
  * Write your own callback functions for MMTF.traverse()

* NGL Library/Viewer
  * Play around with examples from gallery on CodePen
  * Load your favorite structure and add your favorite representations
  * Iterate over the atoms in a structure and print a label
  * Calculate B-factor statistics for backbone atoms only
  * Print the distance between two clicked-on atoms
