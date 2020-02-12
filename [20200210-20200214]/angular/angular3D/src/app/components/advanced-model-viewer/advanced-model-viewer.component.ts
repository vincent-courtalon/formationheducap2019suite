import { Component, OnInit, AfterViewInit, Input, ViewChild, ElementRef } from '@angular/core';
import * as THREE from "three-full";
import { ThrowStmt } from '@angular/compiler';

@Component({
  selector: 'app-advanced-model-viewer',
  templateUrl: './advanced-model-viewer.component.html',
  styleUrls: ['./advanced-model-viewer.component.css']
})
export class AdvancedModelViewerComponent implements OnInit, AfterViewInit {

  // la camera 'film' la scene 3d, c'est notre point de vue
  private camera : THREE.PerspectiveCamera;
  // la geometrie (point, aretes, faces) d'un modele
  private mug: THREE.Object3D;
  // source de lumiere ambiante
  private ambientLight : THREE.AmbientLight;
  // source de lumiere directionelle
  private directionalLight : THREE.DirectionalLight;
  // la scene a afficher
  private scene : THREE.Scene;
  // le moteur de rendu
  private renderer: THREE.WebGLRenderer;

  @Input()
  public  rotationSpeedX : number =  0.005;
  @Input()
  public  rotationSpeedY : number =  0.01;

  private _couleur = "#CCCCFF";
  @Input()
  public set couleur(couleur : string)  {
    this._couleur = couleur;
    if (this.mug) {
      this.mug.getObjectByName("coffeMug.001")
              .material.color  = new THREE.Color(couleur);
    }
  }
  
  private _scale = 1.0;
  @Input()
  public set scale(scale : number) {
    this._scale = scale;
    if (this.mug) {
      this.mug.scale.set(scale, scale, scale);
    }
  }
 

  // la balise canvas
  @ViewChild('canvas', {static: true})
  private canvasRef : ElementRef;

  public get canvas() : HTMLCanvasElement {
    // extraction de la balise native sans l'habillage angular
    return this.canvasRef.nativeElement;
  }


  constructor() { }

  ngOnInit() {
  }

  private createScene() : void {
    this.scene = new THREE.Scene();
    // les parametres: angle de vue, aspect ratio, distance de rendu la plus proche, distance de rendu la plus grande
    this.camera = new THREE.PerspectiveCamera(70, this.canvas.clientWidth / this.canvas.clientHeight, 0.1, 2000);
    this.camera.position.set(0,0, 100);
    this.camera.lookAt(0, 0, 0);

    this.ambientLight = new THREE.AmbientLight(0xcccccc, 0.4);
    this.directionalLight = new THREE.DirectionalLight(0xffffff, 0.8);
    // direction de la lumiere
    this.directionalLight.position.set(1, 1, 0).normalize();

    this.scene.add(this.ambientLight);
    this.scene.add(this.directionalLight);

  }

  private createModel() : void {
    // objet permettant de charger un modele collada
    let loader = new THREE.ColladaLoader();
    // charger le modele, et appeler notre méthode quand il est prêt
    loader.load("/assets/models/mug.dae", (model) => {
      this.mug = model.scene;
      this.mug.scale.set(this._scale, this._scale, this._scale);
      let mesh = this.mug.getObjectByName("coffeMug.001");
      this.scene.add(this.mug);
      mesh.material.color = new THREE.Color(this._couleur); //{r: 0.8, g: 0.1, b: 0.2};
    });



  }

  private startRenderingLoop() : void {
    this.renderer = new THREE.WebGLRenderer({ canvas: this.canvas});
    this.renderer.setPixelRatio(devicePixelRatio);
    this.renderer.setSize(this.canvas.clientWidth, this.canvas.clientHeight);

    // mon composant
    let component : AdvancedModelViewerComponent = this;
    // fonction rapellée a chaque rafraichissement
    (function render() {
      // je lui demande de rappelere ma focntion render tous les 60eme de seconde
      requestAnimationFrame(render);
      // animation cube
      component.rotateModel();
      // rendu
      component.renderer.render(component.scene, component.camera);
    })();
  }

  private rotateModel() : void {
    if (this.mug) {
      // todo (rotation)
      this.mug.rotation.z += this.rotationSpeedX;
      this.mug.rotation.y += this.rotationSpeedY;
    }
  }

  public onResize() : void {
    this.camera.aspect = this.canvas.clientWidth / this.canvas.clientHeight;
  }

  // appelé une fois les injections en provenance du contenu parent disponnible
  ngAfterViewInit() : void {
    // creation de la scene
    this.createScene();
    // creation du cube
    this.createModel();
    // rendu
    this.startRenderingLoop();

  }
}
