import '../../global.css';
import { useState, useEffect } from 'react';
import axios from 'axios';
import { Button } from '../ui/button';
import { Search, PlusCircle } from 'lucide-react';
import { Input } from '../ui/input';
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '../ui/table';
import { Dialog } from '@radix-ui/react-dialog';
import { DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle, DialogTrigger } from '../ui/dialog';
import { Label } from '@radix-ui/react-label';

interface Aluno {
  id: number;
  nome: string;
  cpf: string;
}

const ProjetosTable = () => {
  const [alunos, setAlunos] = useState<Aluno[]>([]);
  const [alunoSelecionado, setAlunoSelecionado] = useState<Aluno>({ id: 0, nome: '', cpf: '' });

  useEffect(() => {
    axios.get("http://localhost:8080/alunos")
      .then(response => setAlunos(response.data))
      .catch(error => console.error("Erro ao buscar alunos:", error));
  }, []);

  const handleSave = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const method = alunoSelecionado.id ? 'put' : 'post';
    const url = `http://localhost:8080/alunos${alunoSelecionado.id ? '/' + alunoSelecionado.id : ''}`;

    axios({
      method: method,
      url: url,
      data: alunoSelecionado,
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => {
        const updatedData = response.data;
        setAlunos(prevAlunos => prevAlunos.map(al => al.id === updatedData.id ? updatedData : al));
        setAlunoSelecionado({ id: 0, nome: '', cpf: '' });
      })
      .catch(error => console.error("Erro ao salvar aluno:", error));
  };

  return (
    <div className='p-6 max-w-4xl mx-auto space-y-4'>
      <h1 className='text-4xl font-bold'>Professores</h1>
      <div className='flex items-center justify-between'>
        <form className='flex items-center gap-2'>
          <Input placeholder='Nome do aluno'/>
          <Input placeholder='CPF'/>
          <Button type='submit' variant={'ghost'}>
            <Search className='w-4 h-4 mr-2'/>
            Filtrar resultados
          </Button>
        </form>
        <Dialog>
          <DialogTrigger asChild>
            <Button>
              <PlusCircle className='w-4 h-4 mr-2'/>
              Novo Aluno
            </Button>
          </DialogTrigger>
          <DialogContent>
            <DialogHeader>
              <DialogTitle>Novo Aluno</DialogTitle>
              <DialogDescription>Adicione ou edite um aluno</DialogDescription>
            </DialogHeader>
            <form className='space-y-6' onSubmit={handleSave}>
              <div className='grid grid-cols-4 items-center text-right gap-2 text-nowrap'>
                <Label htmlFor='Nome'>Nome do aluno</Label>
                <Input className='col-span-3' id='Nome' value={alunoSelecionado.nome} onChange={e => setAlunoSelecionado({ ...alunoSelecionado, nome: e.target.value })}/>
              </div>
              <div className='grid grid-cols-4 items-center text-right gap-2 text-nowrap'>
                <Label htmlFor='CPF'>CPF</Label>
                <Input className='col-span-3' id='CPF' value={alunoSelecionado.cpf} onChange={e => setAlunoSelecionado({ ...alunoSelecionado, cpf: e.target.value })}/>
              </div>
              <DialogFooter>
                <Button type='submit'>Salvar</Button>
              </DialogFooter>
            </form>
          </DialogContent>
        </Dialog>
      </div>
      <Table>
        <TableHeader>
          <TableHead>Nome</TableHead>
          <TableHead>CPF</TableHead>
        </TableHeader>
        <TableBody>
          {alunos.map((aluno, index) => (
            <TableRow key={index}>
              <TableCell>{aluno.nome}</TableCell>
              <TableCell>{aluno.cpf}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default ProjetosTable;
