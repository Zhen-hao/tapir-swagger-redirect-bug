let
   sbt-overlay = self: super: {
    sbt = super.sbt.override { jre = super.jdk8; };
  };
  pkgs = import <nixpkgs> {
    config = { allowUnfree = true; };
    overlays = [ sbt-overlay ];
  };
in pkgs.stdenv.mkDerivation {
  name = "dev-shell";
  buildInputs = with pkgs; [
    sbt
    jdk8
  ];
}

