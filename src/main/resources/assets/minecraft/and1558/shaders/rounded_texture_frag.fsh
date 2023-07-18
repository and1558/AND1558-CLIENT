#version 120

uniform sampler2D texture;
uniform vec2 position;
uniform vec2 size;
uniform float radius;

float roundedBoxSDF(vec2 p, vec2 s, vec4 r) {
    r.xy = (p.x > 0.0) ? r.xy : r.zw;
                         r.x  = (p.y > 0.0) ? r.x  : r.y;

                                              vec2 q = abs(p) - s + r.x;
                                                                      return min(max(q.x, q.y), 0.0) + length(max(q, 0.0)) - r.x;
                                                                                                                              }

                                                                                                                              void main() {
                                                                                                                                              vec4 col = vec4(texture2D(texture, gl_TexCoord[0].st));

                                                                                                                                                                                            col.a = 1.0 - roundedBoxSDF(gl_FragCoord.xy - position - size / 2.0, size / 2.0, vec4(radius));

                                                                                                                                                                                                                                                                             gl_FragColor = col;
                                                                                                                                          }