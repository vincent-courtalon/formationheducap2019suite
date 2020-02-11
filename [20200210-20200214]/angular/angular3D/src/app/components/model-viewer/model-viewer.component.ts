import { Component, OnInit, AfterViewInit, ElementRef, ViewChild, Input } from '@angular/core';

import * as THREE from "three-full";

@Component({
  selector: 'app-model-viewer',
  templateUrl: './model-viewer.component.html',
  styleUrls: ['./model-viewer.component.css']
})
export class ModelViewerComponent implements OnInit, AfterViewInit {

  // la camera 'film' la scene 3d, c'est notre point de vue
  private camera : THREE.PerspectiveCamera;
  // la geometrie (point, aretes, faces) d'un modele
  private cube: THREE.Mesh;
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

  private createCube() : void {
    let texture = null;
    texture = new THREE.TextureLoader().load('/assets/images/bark-1024.jpg');
    // le material, autrement dit ce qui est appliqué sur le mesh
    // par exemple, type metallic, plastic, etc + texture, reflets
    
    //let material = new THREE.MeshPhongMaterial({color: 0xEE1133});
    let material = new THREE.MeshBasicMaterial({map: texture});

    // le squelette d'un cube
    let geometry = new THREE.BoxBufferGeometry(40, 40, 40);
    // le cube, c'est la geometrie + le material appliqué dessus
    this.cube = new THREE.Mesh(geometry, material);
    // ajout à la scene
    this.scene.add(this.cube);

  }

  private startRenderingLoop() : void {
    this.renderer = new THREE.WebGLRenderer({ canvas: this.canvas});
    this.renderer.setPixelRatio(devicePixelRatio);
    this.renderer.setSize(this.canvas.clientWidth, this.canvas.clientHeight);

    // mon composant
    let component : ModelViewerComponent = this;
    // fonction rapellée a chaque rafraichissement
    (function render() {
      // je lui demande de rappelere ma focntion render tous les 60eme de seconde
      requestAnimationFrame(render);
      // animation cube
      component.rotateCube();
      // rendu
      component.renderer.render(component.scene, component.camera);
    })();
  }

  private rotateCube() : void {
    if (this.cube) {
      this.cube.rotation.x += this.rotationSpeedX;
      this.cube.rotation.y += this.rotationSpeedY;
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
    this.createCube();
    // rendu
    this.startRenderingLoop();

  }
}
