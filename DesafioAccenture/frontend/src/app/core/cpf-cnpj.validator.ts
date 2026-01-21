export function isCpf(doc: string): boolean {
  const d = (doc || '').replace(/\D/g, '');
  if (d.length !== 11 || /^(\d)\1+$/.test(d)) return false;
  let sum1 = 0, sum2 = 0;
  for (let i = 0; i < 9; i++) { const n = +d[i]; sum1 += n * (10 - i); sum2 += n * (11 - i); }
  const dv1 = (sum1 % 11 < 2) ? 0 : 11 - (sum1 % 11);
  sum2 += dv1 * 2;
  const dv2 = (sum2 % 11 < 2) ? 0 : 11 - (sum2 % 11);
  return dv1 === +d[9] && dv2 === +d[10];
}

export function isCnpj(doc: string): boolean {
  const d = (doc || '').replace(/\D/g, '');
  if (d.length !== 14 || /^(\d)\1+$/.test(d)) return false;
  const w1 = [5,4,3,2,9,8,7,6,5,4,3,2], w2 = [6,5,4,3,2,9,8,7,6,5,4,3,2];
  let sum1 = 0, sum2 = 0;
  for (let i = 0; i < 12; i++) { const n = +d[i]; sum1 += n * w1[i]; sum2 += n * w2[i]; }
  const dv1 = (sum1 % 11 < 2) ? 0 : 11 - (sum1 % 11);
  sum2 += dv1 * w2[12];
  const dv2 = (sum2 % 11 < 2) ? 0 : 11 - (sum2 % 11);
  return dv1 === +d[12] && dv2 === +d[13];
}
